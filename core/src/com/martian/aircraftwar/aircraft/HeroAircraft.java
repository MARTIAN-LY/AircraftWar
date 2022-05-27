package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.shoot.HeroShootDirect;

public class HeroAircraft extends AbstractAircraft {
    private static int BG_WIDTH = 512;
    private static int BG_HEIGHT = 768;
    /**
     * 英雄机单例模式
     */
    private static Texture HERO_IMAGE = new Texture(Gdx.files.internal("images/hero.png"));
    public static HeroAircraft instance = new HeroAircraft(HERO_IMAGE,
            (BG_WIDTH - HERO_IMAGE.getWidth()) / 2,
            0, 0, 0, 20);

    public static HeroAircraft getInstance() {
        return instance;
    }

    private HeroAircraft(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        shootStrategy = new HeroShootDirect();
    }

    /**
     * 英雄机由玩家控制
     */
    @Override
    public void forward() {}
}
