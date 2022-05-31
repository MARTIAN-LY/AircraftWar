package com.martian.aircraftwar.prop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BombPropFactory implements PropFactory
{
    private Texture BOMB_PROP_IMAGE = new Texture(Gdx.files.internal("images/prop_bomb.png"));
    @Override
    public AbstractProp createProp(float locationX, float locationY, float speedX, float speedY)
    {
        return new BombProp(BOMB_PROP_IMAGE, locationX, locationY, speedX, speedY);
    }
}
