package com.martian.aircraftwar.shoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class EnemyShootDirect implements ShootStrategy
{
    static int shootNum;

    static public void setShootNum(int num)
    {
        shootNum = num;
    }

    @Override
    public List<BaseBullet> doShoot(float x, float y)
    {
        List<BaseBullet> res = new LinkedList<>();
        y -= 2;
        x += 52;
        int speedX = 0;
        int speedY = -300;
        BaseBullet bullet;
        for(int i = 0; i < shootNum; i++)
        {
            bullet = new EnemyBullet(new Texture(Gdx.files.internal("images/bullet_enemy.png")),x + (i * 2 - shootNum + 1) * 20, y, speedX, speedY);
            res.add(bullet);
        }
        return res;
    }
}
