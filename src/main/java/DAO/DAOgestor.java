public class DAOgestor {
    private static DAOgestor gestorDAO = null;

    private DAOgestor() {
    }

    public static DAOgestor getDAOgestor()
    {
        synchronized(DAOgestor.class)
        {
            if (gestorDAO == null) gestorDAO = new DAOgestor();
        }
        return gestorDAO;
    }
}