package com.martian.aircraftwar.shoot;

import com.martian.aircraftwar.basic.GameUtils;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.EnemyBullet;
import com.martian.aircraftwar.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class StraightShoot implements ShootStrategy {


    private int num;
    private float locationX;
    private float locationY;
    private float speedY;
    private int type;

    public StraightShoot(int num, float locationX, float locationY, float speedY, int type) {
        this.num = num;
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedY = speedY;
        this.type = type;
    }

    @Override
    public List<BaseBullet> shoot() {

        List<BaseBullet> res = new LinkedList<>();

        if (type == GameUtils.ENEMY_BULLET) {
            for (int i = 0; i < num; i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                res.add(new HeroBullet(
                        GameUtils.HERO_BULLET_IMAGE,
                        locationX + (i * 2 - num + 1) * 10,
                        locationY,
                        0,
                        speedY));
            }
        } else {
            for (int i = 0; i < num; i++) {
                res.add(new EnemyBullet(
                        GameUtils.HERO_BULLET_IMAGE,
                        locationX + (i * 2 - num + 1) * 10,
                        locationY,
                        0,
                        speedY)
                );
            }
        }
        return res;
    }
}
