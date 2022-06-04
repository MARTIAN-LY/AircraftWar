package com.martian.aircraftwar.game;

import android.app.Activity;
import android.content.Intent;

import com.martian.aircraftwar.application.CommunicateWithAndroid;

public class Communication implements CommunicateWithAndroid {

    private final Activity activity;

    public Communication(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void gotoOnceScore() {
        activity.startActivity(new Intent(activity, OnceScoreActivity.class));
        activity.finish();
    }

}
