package AppService;

import DAO.DAOGestor;
import Objects.User;

public class AuthGestor {
    private static AuthGestor authgestor = null;
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

    public User logIn (int idCard, String password)
    {
        try {
            DAOGestor.getDAOgestor().login(idCard, password);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean register (int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
    {
        try {
            registered = DAOGestor.getDAOgestor().registrarse(idCard, username,password, email, age, gender, occupation, admin);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return registered;
    }

    public void deleteCount()
    {
        DAOGestor.getDAOgestor().deleteUser();
    }
}