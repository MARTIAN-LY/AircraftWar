package com.martian.aircraftwar.prop;

import com.badlogic.gdx.graphics.Texture;
import com.martian.aircraftwar.basic.AbstractFlyingObject;

public abstract class AbstractProp extends AbstractFlyingObject {
    public AbstractProp(Texture image, float locationX, float locationY, float speedX, float speedY) {
        super(image, locationX, locationY, speedX, speedY);
    }
    abstract public void effect();
}
