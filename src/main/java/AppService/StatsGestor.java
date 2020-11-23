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
        return "{\"ageStats\":"+ Arrays.toString(statsByAge()) + ",\"genderStats\":" + Arrays.toString(statsByGender()) + ",\"occuStats\":" +
                Arrays.toString(statsByOccupation()) + ",\"timeStats\":" + Arrays.toString(statsByTime()) + "}";
    }

    public int[] statsByGender(){
        return DAOStatsGestor.getDAOStatsgestor().readPositivesByGender();
    }

    public int[] statsByAge(){
        return DAOStatsGestor.getDAOStatsgestor().readPositivesByAge();
    }

    public int[] statsByOccupation(){
        return DAOStatsGestor.getDAOStatsgestor().readPositivesByOccupation();
    }

    public int[] statsByTime(){
        return DAOStatsGestor.getDAOStatsgestor().readPositivesByTime();
    }
}