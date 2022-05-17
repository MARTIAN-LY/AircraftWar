package com.martian.aircraftwar.prop;

import com.martian.aircraftwar.basic.GameUtils;

public class FirePropFactory implements PropFactory
{
    @Override
    public AbstractProp createProp(float locationX, float locationY, float speedX, float speedY)
    {
        return new HealProp(GameUtils.FIRE_PROP_IMAGE, locationX, locationY, speedX, speedY);
    }
}
