package AppService;

import DAO.DAOStatsGestor;
import Objects.Positive;

import java.util.ArrayList;
import java.util.Arrays;

public class StatsGestor {
    private static StatsGestor statsGestor = null;

    private StatsGestor(){}

    public static StatsGestor getStatsgestor(){
        synchronized (StatsGestor.class)
        {
            if(statsGestor == null) statsGestor = new StatsGestor();
        }
        return statsGestor;
    }
    public String obtainStatistics() {
        return Arrays.toString(statsByAge()) + ";" + Arrays.toString(statsByGender()) + ";" +
                Arrays.toString(statsByOccupation()) + ";" + Arrays.toString(statsByTime());
    }

    public int[] statsByGender(){
        int[] ret = new int[3];
        //ret[0]= DAOStatsGestor.getDAOStatsgestor().readPositivesByGender("Male").size();
        //ret[1]= DAOStatsGestor.getDAOStatsgestor().readPositivesByGender("Female").size();
        //ret[2]= DAOStatsGestor.getDAOStatsgestor().readPositivesByGender("Other").size();
        ret[0]=5;
        ret[1]=6;
        ret[2]=7;
        return ret;
    }

    public int[] statsByAge(){
        int[] ret = new int[100];
        for (int i = 0; i < 100; i++) {
            ret[i] = 9;
        }
        return ret;
    }

    public int[] statsByOccupation(){
        int[] ret = new int[5];
        //ret[0]= DAOStatsGestor.getDAOStatsgestor().readPositivesByOccupation("Unoccupied").size();
        //ret[1]= DAOStatsGestor.getDAOStatsgestor().readPositivesByOccupation("Student").size();
        //ret[2]= DAOStatsGestor.getDAOStatsgestor().readPositivesByOccupation("First Sector").size();
        //ret[3]= DAOStatsGestor.getDAOStatsgestor().readPositivesByOccupation("Second Sector").size();
        //ret[4]= DAOStatsGestor.getDAOStatsgestor().readPositivesByOccupation("Third Sector").size();
        ret[0]=5;
        ret[1]=6;
        ret[2]=7;
        ret[3]=5;
        ret[4]=6;
        return ret;
    }

    public int[] statsByTime(){
        int[] ret = new int[18];
        for (int i = 0; i < 18; i++) {
            ret[i] = 3;
        }
        return ret;
    }
}