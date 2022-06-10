package com.martian.aircraftwar.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TmpScore {
    public static int score;
    public static int mode;
    public static String date;
    public static String playerName = "对手";

    public static void setDate(Date date) {
        TmpScore.date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
                .format(new Date());
    }
}
