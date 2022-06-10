package com.martian.aircraftwar.online

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.martian.aircraftwar.MainActivity
import com.martian.aircraftwar.data.Score
import com.martian.aircraftwar.data.ScoreViewModel
import com.martian.aircraftwar.data.TmpScore
import com.martian.aircraftwar.databinding.ActivityPkResultBinding


class PkResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPkResultBinding
    private lateinit var viewModel: ScoreViewModel
    private val handler = Handler();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPkResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val myName = preferences.getString("username", "我")

        binding.btnPkConfirm.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        /** 显示我的得分 */
        binding.textPkMyname.text = myName;
        binding.textPkMyscore.text = TmpScore.score.toString()

        /** 获取ViewModel 操作数据库,保存本局得分 */
        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]
        viewModel.addScore(Score(0, TmpScore.score, TmpScore.mode, TmpScore.date))

        /** 等待对方完成游戏，更新 UI */
        Thread {
            /** 发送自己的分数 */
            Client.sendMessage(TmpScore.score.toString())
            /** 接收对方的分数  */
            val msg = Client.getMessage()
            val playerScore = Integer.parseInt(msg)

            /** 更新UI */
            handler.post {
                binding.progressBar2.visibility = View.INVISIBLE
                binding.textPkPlayername.text = TmpScore.playerName
                binding.textPkPlayerscore.text = msg
                binding.textPkPlayername.visibility = View.VISIBLE
                binding.textPkPlayerscore.visibility = View.VISIBLE
                binding.btnPkConfirm.visibility = View.VISIBLE
                if (TmpScore.score == playerScore) {
                    binding.textPkResult.text = "平局"
                } else if (TmpScore.score > playerScore) {
                    binding.textPkResult.text = "YOU WIN!!!"
                    binding.textPkResult.setTextColor(Color.parseColor("#ffffbb33"))
                } else {
                    binding.textPkResult.text = "YOU LOSE!!!"
                    binding.textPkResult.setTextColor(Color.parseColor("#BBBBBB"))
                }
            }
            Client.closeSocket()
        }.start()


    }
}