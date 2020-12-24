package AppService;

import DAO.DAOGestor;
import Objects.User;

/**
 * AppService que delega las llamadas relacionadas con la autenticacion al {@link DAOGestor}.
 * @author Alumno
 *
 */
public class AuthGestor {
    public static AuthGestor authgestor = null;
    User usuario = null;
    boolean registered = false;
    private AuthGestor(){}

    /**
     * Metodo que crea o recupera la instancia del {@link AuthGestor}. Sigue el patron Singleton.
     * @return authgestor
     */
    public static AuthGestor getGestorAuth()
    {
        synchronized(AuthGestor.class)
        {
            if (authgestor == null) authgestor = new AuthGestor();
        }
        return authgestor;
    }

    /**
     * Metodo para hacer logIn en RadarCovid. Delega la funcion en el {@link DAOGestor#login(int, String)}.
     * @param idCard
     * @param password
     * @return error (si el logIn es incorrecto)
     */
    public String logIn (int idCard, String password)
    {
        try {
            return DAOGestor.getDAOgestor().login(idCard, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    /**
     * Metodo para registrarse en RadarCovid. Delega la funcion en el {@link DAOGestor#registrarse(int, String, String, String, int, String, String, boolean)}.
     * @param idCard
     * @param username
     * @param password
     * @param email
     * @param age
     * @param gender
     * @param occupation
     * @param admin
     * @return error (si el registro es incorrecto)
     */
    public String register (int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        try {
           return DAOGestor.getDAOgestor().registrarse(idCard, username,password, email, age, gender, occupation, admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    /**
     * Metodo para borrar la cuenta en RadarCovid. Delega la funcion en el {@link DAOGestor#deleteUser()}.
     */
    public void deleteCount()
    {
        DAOGestor.getDAOgestor().deleteUser();
    }
}