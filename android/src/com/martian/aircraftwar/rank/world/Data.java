package com.martian.aircraftwar.rank.world;

import java.io.Serializable;

/**
 *@Author Martian
 **/

public class Data implements Serializable {
    public String name;
    public int score;

    public Data(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
