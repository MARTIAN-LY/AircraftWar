package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.basic.AbstractFlyingObject;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.shoot.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAircraft extends AbstractFlyingObject {

    int hp;

    ShootStrategy shootStrategy;

    public AbstractAircraft(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY);
        this.hp = hp;
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if (hp <= 0) {
            hp = 0;
            vanish();
        }
    }

    public List<BaseBullet> shoot(){
        return shootStrategy.shoot();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setShootStrategy(ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }

}
