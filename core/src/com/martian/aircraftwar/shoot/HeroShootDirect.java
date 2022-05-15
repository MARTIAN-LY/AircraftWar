package com.martian.aircraftwar.shoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class HeroShootDirect implements ShootStrategy
{
    @Override
    public List<BaseBullet> doShoot(float x, float y)
    {
        int shootNum = 1;
        List<BaseBullet> res = new LinkedList<>();
        x += 50;
        y += 85;
        int speedX = 0;
        int speedY = 150;
        BaseBullet bullet;
        for(int i = 0; i < shootNum; i++)
        {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(new Texture(Gdx.files.internal("images/bullet_hero.png")), x, y, speedX, speedY);
            res.add(bullet);
        }
        return res;
    }
}
