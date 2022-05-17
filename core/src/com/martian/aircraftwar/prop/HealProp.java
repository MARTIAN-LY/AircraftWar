package com.martian.aircraftwar.prop;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.aircraft.HeroAircraft;

public class HealProp extends AbstractProp
{
    public HealProp(Texture image, float locationX, float locationY, float speedX, float speedY)
    {
        super(image, locationX, locationY, speedX, speedY);
    }

    @Override
    public void effect()
    {
        HeroAircraft heroAircraft = HeroAircraft.getInstance();
        heroAircraft.setHp(heroAircraft.getHp() + 1);
    }

}
