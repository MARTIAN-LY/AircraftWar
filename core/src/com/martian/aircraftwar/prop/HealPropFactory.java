package com.martian.aircraftwar.prop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HealPropFactory implements PropFactory
{
    private static Texture HEAL_PROP_IMAGE = new Texture(Gdx.files.internal("images/prop_blood.png"));
    @Override
    public AbstractProp createProp(float locationX, float locationY, float speedX, float speedY)
    {
        return new HealProp(HEAL_PROP_IMAGE, locationX, locationY, speedX, speedY);
    }
}
