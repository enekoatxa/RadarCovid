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
    	DAOAuthGestor.getDAOAuthgestor().deleteUser();
    	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient, 100, 200, 2020, 11, 02);
    	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient2, 100, 200, 2020, 11, 02);
   // 	DAOPositiveGestor.getDAOPositivegestor().deletePositive(1);


    }

    public void iniciarSesion (String idCard, String password)
    {
        try {
            DAOAuthGestor.getDAOAuthgestor().logIn(idCard, password);
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente inicie la sesion del usuario.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void registrarse (int idCard, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        try {
            DAOAuthGestor.getDAOAuthgestor().registerUser(idCard, password, email, age, gender, occupation, admin);
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente registre al usuario.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteUser(String idCard, String password) {
        try {
            DAOAuthGestor.getDAOAuthgestor().deleteUser(idCard, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerPositive(User patient, double latitude, double longitude, int year, int month, int day) {
        try {
            DAOGestor.getDAOgestor().registerPositive(patient, latitude, longitude, year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
