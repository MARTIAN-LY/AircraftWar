package com.martian.aircraftwar.data;

import java.util.Date;

public class TmpScore {
    public static int score;
    public static Date date;

    static {
        score = 0;
        date = new Date();
    }
}
