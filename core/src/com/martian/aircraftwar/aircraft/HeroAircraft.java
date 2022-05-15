package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.basic.GameUtils;
import com.martian.aircraftwar.shoot.HeroShootDirect;
import com.martian.aircraftwar.shoot.HeroShootScattered;

public class HeroAircraft extends AbstractAircraft {

    /**
     * 英雄机单例模式
     */
    public static HeroAircraft instance = new HeroAircraft(GameUtils.HERO_IMAGE,
            (GameUtils.BG_WIDTH - GameUtils.HERO_IMAGE.getWidth()) / 2,
            0, 0, 0, 3);

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
