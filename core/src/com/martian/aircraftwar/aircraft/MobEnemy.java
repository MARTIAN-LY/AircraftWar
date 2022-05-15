package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.graphics.Texture;

public class MobEnemy extends AbstractAircraft{

    public MobEnemy(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
    }

}
