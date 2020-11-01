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
    public void ObtainStatistics() {
        // This method will be used in order to "paint" the Statistics html table.
        // It will posibly call a SQL procedure
    }
}