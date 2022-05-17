package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.math.MathUtils;
import com.martian.aircraftwar.basic.GameUtils;

public class BossEnemyFactory implements EnemyFactory{

    @Override
    public AbstractAircraft createEnemy() {
        return new BossEnemy(
                GameUtils.BOSS_IMAGE,
                MathUtils.random(0, GameUtils.BG_WIDTH - GameUtils.BOSS_IMAGE.getWidth()),
                GameUtils.BG_HEIGHT - 250,
                100,
                0,
                20);
    }
}
