package com.martian.aircraftwar.prop;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.aircraft.HeroAircraft;
import com.martian.aircraftwar.shoot.HeroShootDirect;
import com.martian.aircraftwar.shoot.HeroShootScattered;

public class FireProp extends AbstractProp
{
    public FireProp(Texture image, float locationX, float locationY, float speedX, float speedY)
    {
        super(image, locationX, locationY, speedX, speedY);
    }


    private final Object fireLock = new Object();
    @Override
    public void effect()
    {
        final HeroAircraft heroAircraft = HeroAircraft.getInstance();
        Runnable r = () ->
        {
            long beginTime = System.currentTimeMillis();
            synchronized(fireLock)
            {
                try
                {
                    long lasTime = 4000 - (System.currentTimeMillis() - beginTime);
                    if(lasTime > 0)
                    {
                        heroAircraft.setShootStrategy(new HeroShootScattered());
                        Thread.sleep(lasTime);
                        heroAircraft.setShootStrategy(new HeroShootDirect());
                    }
                } catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                fireLock.notify();
            }
        };
        new Thread(r).start();
    }
}
