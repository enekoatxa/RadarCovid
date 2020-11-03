package AppService;

import DAO.DAOGestor;
import Objects.User;

public class AuthGestor {
    private static AuthGestor authgestor = null;
    User usuario = null;
    boolean registered = false;
    private AuthGestor(){}

    public static AuthGestor getGestorAuth()
    {
        synchronized(AuthGestor.class)
        {
            if (authgestor == null) authgestor = new AuthGestor();
        }
        return authgestor;
    }

    public User logIn (String userName, String password)
    {
        try {
            DAOGestor.getDAOgestor().iniciarSesion(userName, password);
            // usuario = new User(72835127, "juan", "juansolozabal1@gmail.com", 22, "Male", "Student", false);
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente inicie la sesion del usuario.
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
            // Aqui ira el metodo en el que se asociara con el gestorDAO, que sera el que especificamente registre al usuario.
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return registered;
    }

    public void deleteCount(String userName, String password)
    {
        DAOGestor.getDAOgestor().deleteUser(userName, password);
    }
}