package DAO;

import java.util.ArrayList;

import javax.jdo.*;
import javax.sql.RowSet;

import AppService.AuthGestor;
import Objects.User;
import Objects.Positive;
import lombok.*;
/**
 * Gestor DAO que recoge las llamadas del AppService y delega el trabajo en los demas gestores
 * @author Alumno
 *
 */
public class DAOGestor {

    private static DAOGestor gestorDAO = null;
    public boolean registered = false;
    public static ArrayList<User> users = new ArrayList<User>(); //Aqui se guardaran los usuarios que hay en la BD remota.
    public static ArrayList<Positive> positives = new ArrayList<Positive>(); //Aqui se guardaran los positivos que hay en la BD remota.
    public static User userLogged = new User(1,"","","",1,"","",false);

    private DAOGestor() {}
    /**
     * Metodo que crea o recupera la instancia del {@link DAOGestor}. Sigue el patron Singleton.
     * @return gestorDAO
     */
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
    	System.out.println("---------------------------------------------");

    	//DAOAuthGestor.getDAOAuthgestor().registerUser(894, "primero", "1234", "landerp@opendeusto.es", 22, "M", "Student", false);
    	System.out.println(DAOGestor.getDAOgestor().login(999, "1234"));

    	//DAOPositiveGestor.getDAOPositivegestor().registerPositive(userLogged, 100, 200, 2020, 11, 02);
    	//DAOGestor.getDAOgestor().deleteUser();

    }

    //USER METHODS
    /**
     * Metodo que recupera la lista de usuarios de RadarCovid. Delega el trabajo en el {@link DAOAuthGestor#selectUsers()}.
     */
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
    /**
     * Metodo que comprueba el login con los usuarios de la base de datos. Coteja los usuarios con la lista de @see {@link DAOGestor#usersList()}
     * @param idCard
     * @param password
     * @return el usuario logeado
     */
    public String login (int idCard, String password)
    {
        usersList();
        for (User aux : users)
        {
            if(aux.getIdCard()==idCard)
            {
                if (aux.getPassword().equals(password)){
                    userLogged=aux;
                    return userLogged.toString();
                } else {
                    return "errorPass";
                }
            }
        }
        return "errorUser";
    }
    /**
     * Metodo para registrarse en RadarCovid. Delega la funcion en el {@link DAOAuthGestor#registerUser(int, String, String, String, int, String, String, boolean)}.
     * @param idCard
     * @param username
     * @param password
     * @param email
     * @param age
     * @param gender
     * @param occupation
     * @param admin
     * @return
     */
    public String registrarse (int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        String response="";
        try {
            response = DAOAuthGestor.getDAOAuthgestor().registerUser(idCard, username, password, email, age, gender, occupation, admin);
            if(response.equals("true"))
                registered = true;
            usersList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    /**
     * Metodo para borrar un usuario de RadarCovid. Delega la funcion en el {@link DAOAuthGestor#deleteUser()}
     */
    public void deleteUser() {
        try {
            DAOAuthGestor.getDAOAuthgestor().deleteUser();
            userLogged.setIdCard(1);
            userLogged.setUsername("");
            userLogged.setPassword("");
            userLogged.setEmail("");
            userLogged.setAge(1);
            userLogged.setGender("");
            userLogged.setOccupation("");
            userLogged.setAdmin(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    //COVID-19 METHODS
    /**
     * Metodo para regitrar positivo en RadarCovid. Delega la funcion en el {@link DAOPositiveGestor#registerPositive(double, double, int, int, int)}
     * @param latitude
     * @param longitude
     * @param year
     * @param month
     * @param day
     * @return
     */
    public boolean registerPositive(double latitude, double longitude, int year, int month, int day) {
        try {
            return DAOPositiveGestor.getDAOPositivegestor().registerPositive(latitude, longitude, year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Metodo para obtener la lista de positivos de RadarCovid. Delega la funcion en el {@link DAOPositiveGestor#selectPositives()}
     * @return 
     */
    public static String positivesList()
    {
        try {
            return DAOPositiveGestor.getDAOPositivegestor().selectPositives();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean alertOfRisk(double latitude, double longitude){
        return DAOPositiveGestor.getDAOPositivegestor().alarm(latitude, longitude);
    }
}
