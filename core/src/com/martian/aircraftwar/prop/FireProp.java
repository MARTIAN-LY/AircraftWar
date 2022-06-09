package com.martian.aircraftwar.prop;

import static sun.swing.MenuItemLayoutHelper.max;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.aircraft.HeroAircraft;
import com.martian.aircraftwar.shoot.HeroShootDirect;
import com.martian.aircraftwar.shoot.HeroShootScattered;
import com.martian.aircraftwar.application.ScreenGame;

public class FireProp extends AbstractProp
{
    public FireProp(Texture image, float locationX, float locationY, float speedX, float speedY)
    {
        super(image, locationX, locationY, speedX, speedY);
    }


    private static long endTime = 0;
    @Override
    public void effect()
    {
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        Runnable r = () ->
        {
            long beginTime = System.currentTimeMillis();
            if(beginTime + 5000 > endTime)
                endTime = beginTime + 5000;
            heroAircraft.setShootStrategy(new HeroShootScattered());
            HeroShootScattered.setShootNum(6);
            try
            {
                Thread.sleep(5000);
                if(System.currentTimeMillis() >= endTime)
                {
                    heroAircraft.setShootStrategy(new HeroShootDirect());
                    HeroShootScattered.setShootNum(3);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
    }
}
