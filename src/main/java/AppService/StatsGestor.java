package AppService;

import DAO.DAOGestor;
import DAO.DAOStatsGestor;
import Objects.Positive;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * AppService que delega las llamadas relacionadas con la obtencion de estadisticas al {@link DAOGestor}.
 * @author Alumno
 *
 */
public class StatsGestor {
    private static StatsGestor statsGestor = null;

    private StatsGestor(){}
    /**
     * Metodo que crea o recupera la instancia del {@link StatsGestor}. Sigue el patron Singleton.
     * @return statsGestor
     */
    public static StatsGestor getStatsgestor(){
        synchronized (StatsGestor.class)
        {
            if(statsGestor == null) statsGestor = new StatsGestor();
        }
        return statsGestor;
    }
    /**
     * Metodo para pasar a string las estadisticas recogidas del {@link DAOStatsGestor#readAllStats()}.
     * @return string
     */
    public String obtainStatistics() {
        DAOStatsGestor.getDAOStatsgestor().readAllStats();
        return "{\"ageStats\":"+ Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsAge()) +
                ",\"genderStats\":" + Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsGender()) +
                ",\"occuStats\":" + Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsOccupation()) +
                ",\"timeStats\":" + Arrays.toString(DAOStatsGestor.getDAOStatsgestor().getStatsTime()) + "}";
    }
}