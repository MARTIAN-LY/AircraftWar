package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.shoot.EnemyShootDirect;

public class EliteEnemy extends AbstractAircraft {

    public EliteEnemy(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new EnemyShootDirect();
    }
}
