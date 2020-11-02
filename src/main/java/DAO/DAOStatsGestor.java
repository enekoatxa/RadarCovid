package DAO;


public class DAOStatsGestor {
    private static DAOStatsGestor gestorStatsDAO = null;

    private DAOStatsGestor() {
    }

    public static DAOStatsGestor getDAOStatsgestor()
    {
        synchronized(DAOStatsGestor.class)
        {
            if (gestorStatsDAO == null) gestorStatsDAO = new DAOStatsGestor();
        }
        return gestorStatsDAO;
    }
}

