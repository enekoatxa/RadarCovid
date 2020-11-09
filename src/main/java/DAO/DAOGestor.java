package DAO;

import java.util.ArrayList;

import javax.jdo.*;
import javax.sql.RowSet;

import Objects.User;
import Objects.Positive;
import lombok.*;

public class DAOGestor {

    private static DAOGestor gestorDAO = null;
    public boolean registered = false;
    public static ArrayList<User> users = new ArrayList<User>(); //Aqui se guardaran los usuarios que hay en la BD remota.
    public static ArrayList<Positive> positives = new ArrayList<Positive>(); //Aqui se guardaran los positivos que hay en la BD remota.
    public static User userLogged = new User(1,"","","",1,"","",false);

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
    	//positivesList();
    	for(Positive aux: positives){
    		System.out.println("IdPositive: "+aux.getIdPositive()+", Latitude: "+ aux.getLatitude()+
    				", Longitude: "+ aux.getLongitude() + ", Year: "+ aux.getYear()+ ", Month: "+ aux.getMonth()+ ", Day: "+aux.getDay());
    	}
    	System.out.println("---------------------------------------------");

    	login(11111111, "1234");
    	DAOAuthGestor.getDAOAuthgestor().deleteUser();

    	//DAOAuthGestor.getDAOAuthgestor().registerUser(5555,"manolo", "1234", "jon@gmail.com", 21, "M", "Student", true);
    	//DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient, 100, 200, 2020, 11, 02);
    	//DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient2, 100, 200, 2020, 11, 02);

    }

    //USER METHODS
    
    public static void login (int idCard, String password)
    {
        usersList();
        for (User aux : users)
        {
            if(aux.getIdCard()==idCard && aux.getPassword().equals(password))
            {
                userLogged.setIdCard(aux.getIdCard());
                userLogged.setUsername(aux.getUsername());
                userLogged.setPassword(aux.getPassword());
                userLogged.setEmail(aux.getEmail());
                userLogged.setAge(aux.getAge());
                userLogged.setGender(aux.getGender());
                userLogged.setOccupation(aux.getOccupation());
                userLogged.setAdmin(aux.isAdmin());
            }
        }
        System.out.println("Usuario login: "+userLogged.getUsername());
    }

    public boolean registrarse (int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        try {

            registered = DAOAuthGestor.getDAOAuthgestor().registerUser(idCard, username, password, email, age, gender, occupation, admin);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return registered;
    }

    public void deleteUser() {
        try {
            DAOAuthGestor.getDAOAuthgestor().deleteUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void usersList()
    {
        try {
            users.clear();
            DAOAuthGestor.getDAOAuthgestor().selectUsers();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //COVID-19 METHODS

    public boolean registerPositive(User patient, double latitude, double longitude, int year, int month, int day) {
        boolean ok = false;
        try {
        	DAOPositiveGestor.getDAOPositivegestor().registerPositive(patient, latitude, longitude, year, month, day);
        	ok = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public static void positivesList()
    {
        try {
            DAOPositiveGestor.getDAOPositivegestor().selectPositives();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
