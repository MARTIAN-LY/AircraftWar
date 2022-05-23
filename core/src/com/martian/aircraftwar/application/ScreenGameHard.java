package com.martian.aircraftwar.application;

import com.martian.aircraftwar.aircraft.BossEnemyFactory;
import com.martian.aircraftwar.aircraft.EliteEnemyFactory;
import com.martian.aircraftwar.shoot.EnemyShootDirect;
import com.martian.aircraftwar.shoot.EnemyShootScattered;
import com.martian.aircraftwar.shoot.HeroShootDirect;

public class ScreenGameHard extends ScreenGame{
    public ScreenGameHard(AircraftWarGame game)
    {
        super(game);
        this.enemyMaxNumber = 6;
        this.BossInterval = 100;
        this.eliteEnemyRate = 50;
        HeroShootDirect.setShootNum(1);
        EnemyShootDirect.setShootNum(2);
        EnemyShootScattered.setShootNum(5);
        EliteEnemyFactory.setHp(2);
        BossEnemyFactory.setHp(30);
    }
}