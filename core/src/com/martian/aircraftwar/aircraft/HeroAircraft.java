package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.basic.GameUtils;
import com.martian.aircraftwar.shoot.StraightShoot;

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

    /**
     * 最大射击数量
     */
    private final int maxSootNum;
    /**
     * 子弹数
     */
    private int shootNum;


    private HeroAircraft(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        maxSootNum = 5;
        shootNum = 1;
        shootStrategy = new StraightShoot(shootNum,locationX,
                locationY+width/2,
                100,
                GameUtils.HERO_BULLET
        );
    }

    /**
     * 英雄机由玩家控制
     */
    @Override
    public void forward() {}
}
