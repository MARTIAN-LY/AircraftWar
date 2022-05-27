package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.shoot.EnemyShootNothing;

public class MobEnemy extends AbstractAircraft{

    public MobEnemy(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new EnemyShootNothing();
    }

}
