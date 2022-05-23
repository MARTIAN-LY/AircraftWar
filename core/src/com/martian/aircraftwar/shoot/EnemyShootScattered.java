package com.martian.aircraftwar.shoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.EnemyBullet;
import com.martian.aircraftwar.bullet.EnemyMissile;

import java.util.LinkedList;
import java.util.List;

public class EnemyShootScattered implements ShootStrategy
{
    static int shootNum = 5;
    static int nextMissile = 5;
    static Sound missileSound = Gdx.audio.newSound(Gdx.files.internal("videos/missile.wav"));

    static public void setShootNum(int num)
    {
        shootNum = num;
        if(num == 5) {
            nextMissile = 5;
        }
    }

    @Override
    public List<BaseBullet> doShoot(float x, float y)
    {
        List<BaseBullet> res = new LinkedList<>();
        int direction = 1;
        int speedX = -(shootNum / 2) * 10;
        int speedY = -300;
        y -= 2;
        x += 150;
        BaseBullet bullet;
        for(int i = 0; i < shootNum; i++)
        {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(new Texture(Gdx.files.internal("images/bullet_enemy.png")), x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY);
            res.add(bullet);
            speedX += 10;
        }
        if(shootNum == 5)
        {
            nextMissile = (nextMissile + 4) % 5;
            if(nextMissile == 0)
            {
                for(int i = 0; i < 3; i++)
                {
                    bullet = new EnemyMissile(new Texture(Gdx.files.internal("images/missile.png")), x + (i - 1) * 60, y, 0, speedY * 2);
                    res.add(bullet);
                }
                missileSound.play();
            }
        }
        return res;
    }
}
