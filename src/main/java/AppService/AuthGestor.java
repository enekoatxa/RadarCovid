package AppService;

import DAO.DAOGestor;
import Objects.User;

public class AuthGestor {
    public static AuthGestor authgestor = null;
    User usuario = null;
    boolean registered = false;
    private AuthGestor(){}

    //Singleton Patron
    public static AuthGestor getGestorAuth()
    {
        synchronized(AuthGestor.class)
        {
            if (authgestor == null) authgestor = new AuthGestor();
        }
        return authgestor;
    }

    public String logIn (int idCard, String password)
    {
        try {
            return DAOGestor.getDAOgestor().login(idCard, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public String register (int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        try {
           return DAOGestor.getDAOgestor().registrarse(idCard, username,password, email, age, gender, occupation, admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public void deleteCount()
    {
        DAOGestor.getDAOgestor().deleteUser();
    }
}