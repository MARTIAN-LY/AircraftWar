package com.martian.aircraftwar.prop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.aircraft.AbstractAircraft;
import com.martian.aircraftwar.aircraft.BossEnemy;
import com.martian.aircraftwar.aircraft.HeroAircraft;
import com.martian.aircraftwar.basic.AbstractFlyingObject;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.EnemyBullet;
import com.martian.aircraftwar.shoot.HeroShootDirect;
import com.martian.aircraftwar.shoot.HeroShootScattered;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BombProp extends AbstractProp
{
    static private Sound bombSound = Gdx.audio.newSound(Gdx.files.internal("videos/bomb_explosion.wav"));
    public BombProp(Texture image, float locationX, float locationY, float speedX, float speedY)
    {
        super(image, locationX, locationY, speedX, speedY);
    }
    @Override
    public void effect()
    {
    }
    public int effect(LinkedList<AbstractAircraft> enemies, LinkedList<BaseBullet>bullets)
    {
        bombSound.play();
        int res = 0;
        for (Iterator<AbstractAircraft> iterator = enemies.iterator(); iterator.hasNext(); ) {
            AbstractAircraft aircraft = iterator.next();
            //超出屏幕或not valid
            if (aircraft.notValid() || aircraft instanceof BossEnemy)
            {
                continue;
            }
            aircraft.vanish();
            res += 10;
            iterator.remove();
        }
        bullets.removeIf(bullet -> bullet instanceof EnemyBullet);
        return res;
    }
}
