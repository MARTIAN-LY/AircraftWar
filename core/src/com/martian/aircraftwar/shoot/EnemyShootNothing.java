package com.martian.aircraftwar.shoot;

import com.martian.aircraftwar.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class EnemyShootNothing implements ShootStrategy
{
    @Override
    public List<BaseBullet> doShoot(float x, float y)
    {
        return new LinkedList<>();
    }
}
