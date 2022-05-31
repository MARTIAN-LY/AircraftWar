package com.martian.aircraftwar.rank;

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
    public RankingDao()
    {
        Scanner dataIn = null;
        try
        {
            dataIn = new Scanner(Paths.get("rankingData/Rankings.txt"), String.valueOf(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        rankings = new ArrayList<>();
        System.out.println("GGGGGGG");
        while(dataIn != null && dataIn.hasNext())
        {
            String name = dataIn.next();
            System.out.println(name);
            int score = dataIn.nextInt();
            long time = dataIn.nextLong();
            Ranking ranking = new Ranking(name, score, time);
            rankings.add(ranking);
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
        System.out.println(ps + 1);
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
        System.out.println("FFFFFFFFFFFFFFK");
        PrintWriter dateOut = new PrintWriter("rankingData/Rankings.txt", String.valueOf(StandardCharsets.UTF_8));
        for(Ranking ranking: rankings)
        {
            dateOut.print(ranking.getName() + " ");
            dateOut.print(ranking.getScore());
            dateOut.print(" ");
            dateOut.println(ranking.getTime());
        }
        dateOut.flush();
    }
}
