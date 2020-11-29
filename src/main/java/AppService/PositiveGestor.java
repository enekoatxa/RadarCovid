package AppService;

import DAO.DAOGestor;
import DAO.DAOPositiveGestor;
import Objects.User;

public class PositiveGestor {
    private static PositiveGestor positivegestor = null;

    private PositiveGestor(){}

    public static PositiveGestor getPositivegestor(){
        synchronized (PositiveGestor.class)
        {
            if(positivegestor == null) positivegestor = new PositiveGestor();
        }
        return positivegestor;
    }

    public boolean registerPositive(double latitude, double longitude, int year, int month, int day) {
        return DAOGestor.getDAOgestor().registerPositive(latitude, longitude, year, month, day);
    }

    public String searchPositives() {
        return DAOPositiveGestor.getDAOPositivegestor().selectPositives();
    }
}




