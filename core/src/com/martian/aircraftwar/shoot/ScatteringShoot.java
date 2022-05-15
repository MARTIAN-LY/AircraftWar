package com.martian.aircraftwar.shoot;

import com.martian.aircraftwar.basic.GameUtils;
import com.martian.aircraftwar.bullet.BaseBullet;

import java.util.List;

public class ScatteringShoot implements ShootStrategy{

    private int num;
    private float locationX;
    private float locationY;
    private float speedY;
    private int type;

    public ScatteringShoot(int num, float locationX, float locationY, float speedY, int type) {
        this.num = num;
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedY = speedY;
        this.type = type;
    }

    @Override
    public List<BaseBullet> shoot() {
        if (type == GameUtils.ENEMY_BULLET){
            return null;
        } else {
            return null;
        }
    }
}
