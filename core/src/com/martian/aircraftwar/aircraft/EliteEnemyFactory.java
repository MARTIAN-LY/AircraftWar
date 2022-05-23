package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.math.MathUtils;
import com.martian.aircraftwar.basic.GameUtils;

public class EliteEnemyFactory implements EnemyFactory{

    static public int hp;
    static public void setHp(int val)
    {
        hp = val;
    }
    @Override
    public AbstractAircraft createEnemy() {
        return new EliteEnemy(
                GameUtils.ELITE_IMAGE,
                MathUtils.random(0, GameUtils.BG_WIDTH - GameUtils.ELITE_IMAGE.getWidth()),
                GameUtils.BG_HEIGHT,
                0,
                -200,
                hp);
    }
}
