public class DAOAuthgestor {
    private static DAOAuthgestor gestorAuthDAO = null;

    private DAOAuthgestor() {
    }

    public static DAOAuthgestor getDAOAuthgestor()
    {
        synchronized(DAOAuthgestor.class)
        {
            if (gestorAuthDAO == null) gestorAuthDAO = new DAOAuthgestor();
        }
        return gestorAuthDAO;
    }
}