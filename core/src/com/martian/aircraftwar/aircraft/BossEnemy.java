package com.martian.aircraftwar.aircraft;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.martian.aircraftwar.prop.AbstractProp;
import com.martian.aircraftwar.prop.BombPropFactory;
import com.martian.aircraftwar.prop.FirePropFactory;
import com.martian.aircraftwar.prop.HealPropFactory;
import com.martian.aircraftwar.prop.PropFactory;
import com.martian.aircraftwar.shoot.EnemyShootScattered;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft {

    public BossEnemy(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new EnemyShootScattered();
    }
    @Override
    public List<AbstractProp> dropProp()
    {
        List<AbstractProp> res = new LinkedList<>();
        float x = this.x;
        float y = this.y;
        int type = MathUtils.random(0, 100);
        PropFactory propFactory;
        if(type <= 33)
        {
            propFactory = new FirePropFactory();
        }
        else if(type <= 66)
        {
            propFactory = new BombPropFactory();
        }
        else
        {
            propFactory = new HealPropFactory();
        }
        res.add(propFactory.createProp(x, y, 0, -150));
        return res;
    }
}
