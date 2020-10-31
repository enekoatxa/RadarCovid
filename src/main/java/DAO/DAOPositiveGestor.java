public class DAOPositivegestor {
    private static DAOPositivegestor gestorPositiveDAO = null;

    private DAOPositivegestor() {
    }

    public static DAOPositivegestor getDAOPositivegestor()
    {
        synchronized(DAOPositivegestor.class)
        {
            if (gestorPositiveDAO == null) gestorPositiveDAO = new DAOPositivegestor();
        }
        return gestorPositiveDAO;
    }
}