package com.martian.aircraftwar.shop.goods;

public abstract class Good
{
    public String title;
    public int cost, icon;
    Good(String title, int cost, int icon)
    {
        this.title = title;
        this.cost = cost;
        this.icon = icon;
    }
}
