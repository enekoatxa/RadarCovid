package DAO;

import java.util.ArrayList;

import javax.jdo.*;
import Objects.User;
import Objects.Positive;
import lombok.*;

public class DAOGestor {

    private static DAOGestor gestorDAO = null;
    public boolean registered = false;
    public static ArrayList<User> users = new ArrayList<User>(); //Aqui se guardaran los usuarios que hay en la BD remota.
    public static ArrayList<Positive> positives = new ArrayList<Positive>(); //Aqui se guardaran los positivos que hay en la BD remota.
    private static User userLogged;

    private DAOGestor() {
    //	userLogged = new User();
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
         * USER(idCard, username, password, email, age, gender, occupation, admin)
         * POSITIVE(user, latitude, longitude, year, month, day)
         */
    	System.out.println("-----------------USERS IN BD-----------------");
    	usersList();
    	for(User aux: users){
    		System.out.println("IdCard: "+aux.getIdCard()+", Username: "+ aux.getUsername()+", Password: "+aux.getPassword()+", Email: "+ aux.getEmail()
    					+", Age: "+ aux.getAge() + ", Gender: "+ aux.getGender()+ ", Occupation: "+ aux.getOccupation()+ ", Admin: "+aux.isAdmin());
    	}
    	System.out.println("---------------------------------------------");
    	System.out.println("---------------POSITIVES IN BD---------------");
    	positivesList();
    	for(Positive aux: positives){
    		System.out.println("IdPositive: "+aux.getIdPositive()+", Latitude: "+ aux.getLatitude()+
    				", Longitude: "+ aux.getLongitude() + ", Year: "+ aux.getYear()+ ", Month: "+ aux.getMonth()+ ", Day: "+aux.getDay());
    	}
//    	for(Positive aux: positives){
//    		System.out.println("IdPositive: "+aux.getIdPositive()+", IdCard: "+aux.getPatient().getIdCard()+", Latitude: "+ aux.getLatitude()+
//    				", Longitude: "+ aux.getLongitude() + ", Year: "+ aux.getYear()+ ", Month: "+ aux.getMonth()+ ", Day: "+aux.getDay());
//    	}
    	System.out.println("---------------------------------------------");

    	User patient = new User(123123123, "jonusername","1234", "jon@gmail.com", 21, "M", "Student", true);
   // 	User patient2 = new User(66666666,"javiusername", "1234", "javi@gmail.com", 21, "M", "Student", true);
    	DAOAuthGestor.getDAOAuthgestor().registerUser(156385628,"jonusername", "1234", "jon@gmail.com", 21, "M", "Student", true);
   // 	DAOAuthGestor.getDAOAuthgestor().registerUser(66666666,"javiusername", "1234", "javi@gmail.com", 21, "M", "Student", true);
   //   DAOAuthGestor.getDAOAuthgestor().deleteUser();
    	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient, 100, 200, 2020, 11, 02);
   // 	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient2, 100, 200, 2020, 11, 02);
   // 	DAOPositiveGestor.getDAOPositivegestor().deletePositive(1);


    }

    public static void usersList()
    {
        try {
            DAOAuthGestor.getDAOAuthgestor().selectUsers();
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente inicie la sesion del usuario.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void positivesList()
    {
        try {
            DAOPositiveGestor.getDAOPositivegestor().selectPositives();
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente inicie la sesion del usuario.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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

    public boolean registrarse (int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        try {

            registered = DAOAuthGestor.getDAOAuthgestor().registerUser(idCard, username, password, email, age, gender, occupation, admin);
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente registre al usuario.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return registered;
    }

    public void deleteUser(String userName, String password) {
        try {
            DAOAuthGestor.getDAOAuthgestor().deleteUser(userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerPositive(User patient, double latitude, double longitude, int year, int month, int day) {
        try {
        	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient, latitude, longitude, year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
