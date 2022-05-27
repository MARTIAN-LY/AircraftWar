package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class MobEnemyFactory implements EnemyFactory{
    private static int BG_WIDTH = 512;
    private static int BG_HEIGHT = 768;
    private static Texture MOB_IMAGE = new Texture(Gdx.files.internal("images/mob.png"));
    @Override
    public AbstractAircraft createEnemy() {
        return new MobEnemy(
                MOB_IMAGE,
                MathUtils.random(0, BG_WIDTH - MOB_IMAGE.getWidth()),
                BG_HEIGHT,
                0,
                -200,
                1);
    }
}
