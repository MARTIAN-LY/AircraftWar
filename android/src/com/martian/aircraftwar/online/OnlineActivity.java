package com.martian.aircraftwar.online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.martian.aircraftwar.data.TmpScore;
import com.martian.aircraftwar.databinding.ActivityOnlineBinding;
import com.martian.aircraftwar.game.AndroidLauncher;


public class OnlineActivity extends AppCompatActivity {

    private ActivityOnlineBinding binding = null;
    private SharedPreferences preferences = null;
    private Handler handler = new Handler();
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnlineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /** 读取自己的用户名 */
        preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String myMame = preferences.getString("username", "我");
        binding.textMyname.setText(myMame);

        /** 尝试去连接服务器，连不上会一直卡住 */
        new Thread(() -> {
            Log.e("========", "尝试连接服务器");
            Client.initConnection();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("========", "检查是否连接");
            if (Client.isConnected()) {
                /** 发送自己的用户名 */
                Client.sendMessage(myMame);
                /** 接收对方的用户名 */
                playerName = Client.getMessage();
                TmpScore.playerName = playerName;

                /** 更新UI */
                handler.post(() -> {
                    Toast.makeText(this, "成功连接到服务器", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    binding.textPlayername.setText(playerName);
                    binding.textPlayername.setVisibility(View.VISIBLE);
                    binding.imagePlayer.setVisibility(View.VISIBLE);
                    binding.textWaiting.setText("匹配成功！");
                    binding.textGameBegin.setVisibility(View.VISIBLE);
                    Log.e("========", "更新UI成功");
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(() -> {
                    Intent intent = new Intent(this, AndroidLauncher.class);
                    intent.putExtra("MODE", 3);
                    startActivity(intent);
                });
            } else {
                Log.e("========", "没有连接到服务器");
                handler.post(() -> {
                    Toast.makeText(this, "未连接到服务器！！！", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        }).start();

    }

}