package com.martian.aircraftwar.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.aircraft.BossEnemy;
import com.martian.aircraftwar.aircraft.BossEnemyFactory;
import com.martian.aircraftwar.aircraft.EliteEnemyFactory;
import com.martian.aircraftwar.shoot.EnemyShootDirect;
import com.martian.aircraftwar.shoot.EnemyShootScattered;
import com.martian.aircraftwar.shoot.HeroShootDirect;

public class ScreenGameNormal extends ScreenGame{
    public ScreenGameNormal(AircraftWarGame game)
    {
        super(game);
        this.background = new Texture(Gdx.files.internal("images/bg2.jpg"));
        this.enemyMaxNumber = 5;
        this.BossInterval = 200;
        this.eliteEnemyRate = 25;
        HeroShootDirect.setShootNum(2);
        EnemyShootDirect.setShootNum(1);
        EnemyShootScattered.setShootNum(3);
        EliteEnemyFactory.setHp(2);
        BossEnemyFactory.setHp(15);
    }
}
