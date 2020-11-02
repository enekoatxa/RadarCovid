package DAO;

import javax.jdo.*;
import Objects.User;
import Objects.Positive;
import lombok.*;

public class DAOGestor {

    private static DAOGestor gestorDAO = null;

    private DAOGestor() {
    }

    public static DAOGestor getDAOgestor()
    {
        synchronized(DAOGestor.class)
        {
            if (gestorDAO == null) gestorDAO = new DAOGestor();
        }
        DAOAuthGestor.getDAOAuthgestor();
        DAOPositiveGestor.getDAOPositivegestor();
        DAOStatsGestor.getDAOStatsgestor();
        return gestorDAO;
    }


// Class to test Datanucleus
    static PersistenceManagerFactory persistentManagerFactory;
    static PersistenceManager persistentManager;
    static Transaction transaction;
    public static void main(String[] args)
    {
        /*
         * USER(idCard, password, email, age, gender, occupation, admin)
         * POSITIVE(idPositive, user, latitude, longitude, year, month, day)
         */
    	
    	User patient = new User(55555555, "1234", "jon@gmail.com", 21, "M", "Student", true);
    	User patient2 = new User(66666666, "1234", "jon@gmail.com", 21, "M", "Student", true);
    	DAOAuthGestor.getDAOAuthgestor().registerUser(55555555, "1234", "jon@gmail.com", 21, "M", "Student", true);
    	DAOAuthGestor.getDAOAuthgestor().registerUser(66666666, "1234", "jon@gmail.com", 21, "M", "Student", true);
    	DAOAuthGestor.getDAOAuthgestor().deleteUser(patient);
    	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient, 100, 200, 2020, 11, 02);
    	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient2, 100, 200, 2020, 11, 02);
   // 	DAOPositiveGestor.getDAOPositivegestor().deletePositive(1);
    	

    }	
}
