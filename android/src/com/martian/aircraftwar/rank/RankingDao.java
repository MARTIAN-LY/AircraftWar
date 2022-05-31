package com.martian.aircraftwar.rank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class RankingDao
{
    private ArrayList<Ranking> rankings;
    private FileHandle handle = Gdx.files.local("rankingData/rankings.txt");
    public RankingDao()
    {
        rankings = new ArrayList<>();
        String tmp = null;
        if(handle.exists())
        {
            tmp = handle.readString();
        }
        if(tmp != null)
        {
            String name = null, cur;
            int score = 0;
            long time = 0;
            int cnt = 0;
            cur = "";
            for(int i = 0; i < tmp.length(); i++)
            {
                if(tmp.charAt(i) == ' ')
                {
                    if(cnt == 0 && !cur.equals(""))
                    {
                        name = cur;
                        cur = "";
                        ++cnt;
                    }
                    else if(cnt == 1 && !cur.equals(""))
                    {
                        score = Integer.parseInt(cur);
                        cur = "";
                        ++cnt;
                    }
                    else if(cnt == 2 && !cur.equals(""))
                    {
                        time = Long.parseLong(cur);
                        cur = "";
                        ++cnt;
                    }
                    if(cnt == 3)
                    {
                        rankings.add(new Ranking(name, score, time));
                        cnt = 0;
                        System.out.println(name + " " + score + " " + time);
                    }
                }
                else cur += tmp.charAt(i);
            }
        }
    }
    public void doAdd(Ranking ranking)
    {
        int sz = rankings.size();
        int ps = -1;
        for(int i = 0; i < sz; i++)
        {
            if(rankings.get(i).getScore() >= ranking.getScore())
            {
                ps = i;
            }
        }
        rankings.add(ps + 1, ranking);
    }
    public void doDelete(int index)
    {
        rankings.remove(index);
    }
    public ArrayList<Ranking> getAllRankings()
    {
        return rankings;
    }
    public void saveToFile() throws IOException
    {
        handle.delete();
        for(Ranking ranking: rankings)
        {
            handle.writeString(ranking.getName() + " ", true);
            handle.writeString(ranking.getScore() + " ", true);
            handle.writeString(ranking.getTime() + " ", true);
        }
        String ccc = handle.readString();
        System.out.println(ccc);
    }
}
