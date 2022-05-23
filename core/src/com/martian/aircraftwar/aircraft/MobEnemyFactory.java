package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.math.MathUtils;
import com.martian.aircraftwar.basic.GameUtils;

public class MobEnemyFactory implements EnemyFactory{

    @Override
    public AbstractAircraft createEnemy() {
        return new MobEnemy(
                GameUtils.MOB_IMAGE,
                MathUtils.random(0, GameUtils.BG_WIDTH - GameUtils.MOB_IMAGE.getWidth()),
                GameUtils.BG_HEIGHT,
                0,
                -200,
                1);
    }
}
