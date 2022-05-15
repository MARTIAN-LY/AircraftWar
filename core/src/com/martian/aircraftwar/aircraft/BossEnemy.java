package com.martian.aircraftwar.aircraft;


import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.shoot.EnemyShootScattered;

public class BossEnemy extends AbstractAircraft {

    public BossEnemy(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new EnemyShootScattered();
    }
}
