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

    public boolean registerPositive(User patient, double latitude, double longitude, int year, int month, int day) {

        // This method will call DAOgestor in order to register a positive in the database
        return DAOGestor.getDAOgestor().registerPositive(patient, latitude, longitude, year, month, day);
    }

    public String searchPositives() {
        return DAOPositiveGestor.getDAOPositivegestor().selectPositives();
    }
}




