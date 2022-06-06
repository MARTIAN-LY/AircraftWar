package com.martian.aircraftwar.shoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.application.ScreenGame;
import com.martian.aircraftwar.bullet.BaseBullet;
import com.martian.aircraftwar.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class HeroShootScattered implements ShootStrategy
{
    private static final  Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("videos/bullet.wav"));
    @Override
    public List<BaseBullet> doShoot(float x, float y)
    {
        int shootNum = 3;
        List<BaseBullet> res = new LinkedList<>();
        x += 50;
        y += 85;
        int speedX = -(shootNum / 2) * 10;
        int speedY = 150;
        BaseBullet bullet;
        Texture HERO_BULLET_IMAGE = new Texture(Gdx.files.internal("images/bullet_hero.png"));
        for(int i = 0; i < shootNum; i++)
        {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(HERO_BULLET_IMAGE, x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY);
            res.add(bullet);
            speedX += 10;
        }
        if(ScreenGame.haveMusic)
        {
            shootSound.play();
        }
        return res;
    }
}
