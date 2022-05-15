package com.martian.aircraftwar.shoot;

import com.martian.aircraftwar.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategy {

    List<BaseBullet> shoot();
}
