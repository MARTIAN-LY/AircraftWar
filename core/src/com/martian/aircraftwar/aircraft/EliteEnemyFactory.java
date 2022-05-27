package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class EliteEnemyFactory implements EnemyFactory{
    private static int BG_WIDTH = 512;
    private static int BG_HEIGHT = 768;
    private static Texture ELITE_IMAGE = new Texture(Gdx.files.internal("images/elite.png"));
    static public int hp;
    static public void setHp(int val)
    {
        hp = val;
    }
    @Override
    public AbstractAircraft createEnemy() {
        return new EliteEnemy(
                ELITE_IMAGE,
                MathUtils.random(0, BG_WIDTH - ELITE_IMAGE.getWidth()),
                BG_HEIGHT,
                0,
                -200,
                hp);
    }
}
