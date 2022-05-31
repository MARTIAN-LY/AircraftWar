package com.martian.aircraftwar.basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class AbstractFlyingObject extends Rectangle {

    /**
     * 图片
     */
    protected Texture image;
    protected float speedX;
    protected float speedY;
    boolean isValid;

    public AbstractFlyingObject(Texture image, float locationX, float locationY, float speedX, float speedY) {
        this.image = image;
        this.speedX = speedX;
        this.speedY = speedY;
        this.x = locationX;
        this.y = locationY;
        this.width = image.getWidth();
        this.height = image.getHeight();
        isValid = true;
    }

    /**
     * 可飞行对象根据速度移动
     * 若飞行对象触碰到横向边界，横向速度反向
     */
    private final float maxRight = 512;

    public void forward() {
        x += Gdx.graphics.getDeltaTime() * speedX;
        y += Gdx.graphics.getDeltaTime() * speedY;
        if (x <= 0)
        {
            speedX = Math.abs(speedX);
            x = 0;
        }
        else if(x + image.getWidth() >= maxRight)
        {
            speedX = -Math.abs(speedX);
            x = maxRight - image.getWidth();
        }
    }

    public boolean crash(AbstractFlyingObject obj) {
        return overlaps(obj);
    }

    public void vanish() {
        isValid = false;
    }

    public boolean notValid() {
        return !isValid;
    }

    public Texture getImage() {
        return image;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setValid()
    {this.isValid = true;}

    public void setLocation(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
}

