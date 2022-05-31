package com.martian.aircraftwar.prop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FirePropFactory implements PropFactory
{
    private Texture FIRE_PROP_IMAGE = new Texture(Gdx.files.internal("images/prop_bullet.png"));
    @Override
    public AbstractProp createProp(float locationX, float locationY, float speedX, float speedY)
    {
        return new FireProp(FIRE_PROP_IMAGE, locationX, locationY, speedX, speedY);
    }
}
