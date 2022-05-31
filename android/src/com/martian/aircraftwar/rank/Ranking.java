package com.martian.aircraftwar.rank;

public class Ranking
{
    private String name;
    private int score;
    private long time;
    public Ranking(String name, int score, long time)
    {
        this.name = name;
        this.score = score;
        this.time = time;
    }
    public String getName()
    {
        return name;
    }
    public int getScore()
    {
        return score;
    }
    public long getTime()
    {
        return time;
    }
}
