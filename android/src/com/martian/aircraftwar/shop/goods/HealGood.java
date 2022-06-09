package com.martian.aircraftwar.shop.goods;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.martian.aircraftwar.R;
import com.martian.aircraftwar.shop.ShopActivity;

public class HealGood extends Good
{
    public HealGood(Context c, ShopActivity a)
    {
        super("解锁主动回血道具", 1000, R.drawable.ic_healgood);
        super.listener.c = c;
        super.listener.a = a;
        SharedPreferences namePreference = this.listener.a.getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = namePreference.getString("username", null);
        SharedPreferences preferences = this.listener.a.getSharedPreferences(name, MODE_PRIVATE);
        boolean haveHeal = preferences.getBoolean("UserHaveHeal", false);
        if(!haveHeal)
        {
            las = 1;
        }
        else
        {
            las = 0;
        }
    }

    @Override
    public void effect()
    {
        SharedPreferences namePreference = this.listener.a.getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = namePreference.getString("username", null);
        SharedPreferences preferences = this.listener.a.getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("UserHaveHeal");
        editor.commit();
        editor.putBoolean("UserHaveHeal", true);
        editor.commit();
        las = 0;
    }
}
