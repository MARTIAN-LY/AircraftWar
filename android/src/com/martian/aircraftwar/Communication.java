package com.martian.aircraftwar;

import android.app.Activity;
import android.content.Intent;

import com.martian.aircraftwar.application.CommunicateWithAndroid;
import com.martian.aircraftwar.rank.RankActivity;

public class Communication implements CommunicateWithAndroid {

    private final Activity activity;

    public Communication(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void gotoRankList() {
        activity.startActivity(new Intent(activity,RankActivity.class));
        activity.finish();
    }

}
