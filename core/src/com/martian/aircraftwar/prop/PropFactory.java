package com.martian.aircraftwar.prop;

public interface PropFactory
{
    public abstract AbstractProp createProp(float locationX, float locationY, float speedX, float speedY);
}
