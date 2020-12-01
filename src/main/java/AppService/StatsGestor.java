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
        DAOStatsGestor.getDAOStatsgestor().readAllStats();
        return "{\"ageStats\":"+ Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsAge()) +
                ",\"genderStats\":" + Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsGender()) +
                ",\"occuStats\":" + Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsOccupation()) +
                ",\"timeStats\":" + Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsTime()) + "}";
    }
}