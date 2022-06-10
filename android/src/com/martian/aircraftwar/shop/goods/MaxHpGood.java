package com.martian.aircraftwar.shop.goods;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.martian.aircraftwar.R;
import com.martian.aircraftwar.shop.ShopActivity;

public class MaxHpGood extends Good
{
    public MaxHpGood(Context c, ShopActivity a)
    {
        super("永久增加血量上限", 100, R.drawable.ic_maxhpgood);
        super.listener.c = c;
        super.listener.a = a;
        SharedPreferences namePreference = listener.a.getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = namePreference.getString("username", null);
        SharedPreferences preferences = listener.a.getSharedPreferences(name, MODE_PRIVATE);
        int MaxHp = preferences.getInt("UserMaxHp", 30);
        this.las = 40 - MaxHp;
    }

    @Override
    public void effect()
    {
        SharedPreferences namePreference = listener.a.getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = namePreference.getString("username", null);
        SharedPreferences preferences = listener.a.getSharedPreferences(name, MODE_PRIVATE);
        int MaxHp = preferences.getInt("UserMaxHp", 30);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("UserMaxHp");
        editor.commit();
        editor.putInt("UserMaxHp", MaxHp + 1);
        editor.commit();
        las -= 1;
    }
}
