package com.martian.aircraftwar.bullet;

import com.badlogic.gdx.graphics.Texture;

public class EnemyMissile extends BaseBullet {
    public EnemyMissile(Texture image, float locationX, float locationY, float speedX, float speedY) {
        super(image, locationX, locationY, speedX, speedY);
        super.setPower(3);
    }
}
