package com.martian.aircraftwar.application;

import com.martian.aircraftwar.aircraft.EliteEnemyFactory;
import com.martian.aircraftwar.shoot.EnemyShootDirect;
import com.martian.aircraftwar.shoot.HeroShootDirect;

public class ScreenGameEasy extends ScreenGame{
    public ScreenGameEasy(AircraftWarGame game)
    {
        super(game);
        this.enemyMaxNumber = 4;
        this.eliteEnemyRate = -1;
        this.BossInterval = Integer.MAX_VALUE;
        HeroShootDirect.setShootNum(2);
        EnemyShootDirect.setShootNum(1);
        EliteEnemyFactory.setHp(1);
    }
}
