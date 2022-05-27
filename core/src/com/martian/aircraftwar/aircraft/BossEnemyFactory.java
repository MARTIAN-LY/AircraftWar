package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class BossEnemyFactory implements EnemyFactory{

    private static int BG_WIDTH = 512;
    private static int BG_HEIGHT = 768;
    private static Texture BOSS_IMAGE = new Texture(Gdx.files.internal("images/boss.png"));
    static public int hp;
    static public void setHp(int val)
    {
        hp = val;
    }
    @Override
    public AbstractAircraft createEnemy() {
        return new BossEnemy(
                BOSS_IMAGE,
                MathUtils.random(0, BG_WIDTH - BOSS_IMAGE.getWidth()),
                BG_HEIGHT - 250,
                100,
                0,
                hp);
    }
}
