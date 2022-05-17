package com.martian.aircraftwar.aircraft;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.martian.aircraftwar.prop.AbstractProp;
import com.martian.aircraftwar.prop.BombPropFactory;
import com.martian.aircraftwar.prop.FirePropFactory;
import com.martian.aircraftwar.prop.HealPropFactory;
import com.martian.aircraftwar.prop.PropFactory;
import com.martian.aircraftwar.shoot.EnemyShootDirect;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends AbstractAircraft {

    public EliteEnemy(Texture image, float locationX, float locationY, float speedX, float speedY, int hp) {
        super(image, locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new EnemyShootDirect();
    }
    @Override
    public List<AbstractProp> dropProp()
    {
        List<AbstractProp> res = new LinkedList<>();
        float x = this.x;
        float y = this.y;
        int type = MathUtils.random(0, 100);
        PropFactory propFactory;
        if(type < 60)
        {
            propFactory = new FirePropFactory();
        }
        else if(type < 80)
        {
            propFactory = new BombPropFactory();
        }
        else
        {
            propFactory = new HealPropFactory();
        }
        res.add(propFactory.createProp(x, y, speedX, speedY / 2));
        return res;
    }
}
