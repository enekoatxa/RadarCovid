package AppService;

import DAO.DAOGestor;
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
    public boolean RegisterPositive(User patient, double latitude, double longitude, int year, int month, int day) {
        // This method will call DAOgestor in order to register a positive in the database
        boolean ok = DAOGestor.getDAOgestor().registerPositive(patient, latitude, longitude, year, month, day);
    }

    public void SearchPositives() {
        // This method will be used to receive all the positives in accordance to the parameterse given by the user
        // Obviously, it won't be a void method, but it is now like that because it is nothing but the skeleton
    }
}




