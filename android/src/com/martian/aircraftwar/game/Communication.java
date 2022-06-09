package com.martian.aircraftwar.game;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import com.martian.aircraftwar.application.CommunicateWithAndroid;

public class Communication implements CommunicateWithAndroid {

    private final Activity activity;
    private int MaxHp;
    private boolean HaveHeal = false;

    public Communication(Activity activity)
    {
        this.activity = activity;
        SharedPreferences namePreference = this.activity.getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = namePreference.getString("username", null);
        SharedPreferences preferences = this.activity.getSharedPreferences(name, MODE_PRIVATE);
        MaxHp = preferences.getInt("UserMaxHp", 30);
        HaveHeal = preferences.getBoolean("UserHaveHeal", false);
    }

    @Override
    public void gotoOnceScore() {
        activity.startActivity(new Intent(activity, OnceScoreActivity.class));
        activity.finish();
    }
    public int getMaxHp()
    {
        return MaxHp;
    }
    public boolean isHaveHeal(){return HaveHeal;}
}
