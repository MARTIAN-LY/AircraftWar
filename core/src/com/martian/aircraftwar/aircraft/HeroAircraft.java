package com.martian.aircraftwar.aircraft;

import static java.lang.Math.min;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.shoot.HeroShootDirect;

public class HeroAircraft extends AbstractAircraft {

    static public HeroAircraft instance;
    static public int MaxHp = 20;
    static public void setMaxHp(int x)
    {
        MaxHp = x;
    }
    static public HeroAircraft getInstance()
    {
        if(instance == null)
        {
            instance = new HeroAircraft(new Texture(Gdx.files.internal("images/hero.png")), (512 - 100) / 2,
                    0, 0, 0, MaxHp);

        }
        return instance;
    }
    static public void refresh()
    {
        instance = null;
    }
    private HeroAircraft(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        shootStrategy = new HeroShootDirect();
    }
    public void addHp(int x)
    {
        this.setHp(min(x + hp, MaxHp));
    }
    /**
     * 英雄机由玩家控制
     */
    @Override
    public void forward() {}
}
