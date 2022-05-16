package com.martian.aircraftwar.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.basic.AbstractFlyingObject;

public class BaseBullet extends AbstractFlyingObject {

    private int power = 1;

    public void setPower(int x)
    {
        power = x;
    }

    public int getPower()
    {
        return power;
    }

    public BaseBullet(Texture image, float locationX, float locationY, float speedX, float speedY) {
        super(image, locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
    }
}
