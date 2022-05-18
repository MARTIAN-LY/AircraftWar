package com.martian.aircraftwar.prop;

import com.martian.aircraftwar.basic.GameUtils;

public class BombPropFactory implements PropFactory
{
    @Override
    public AbstractProp createProp(float locationX, float locationY, float speedX, float speedY)
    {
        return new BombProp(GameUtils.BOMB_PROP_IMAGE, locationX, locationY, speedX, speedY);
    }
}
