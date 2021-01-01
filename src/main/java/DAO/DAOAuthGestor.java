package DAO;

import javax.jdo.*;

import org.apache.log4j.Logger;

import Objects.Positive;
import Objects.User;

import java.time.Clock;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * Gestor DAO que se relaciona con la base de datos remota a traves de Datanucleus para la autenticacion de usuarios.
 * @author Alumno
 *
 */
public class DAOAuthGestor {
    private static DAOAuthGestor gestorAuthDAO = null;
    static Logger logger = Logger.getLogger(DAOAuthGestor.class.getName());
    private DAOAuthGestor() {
    }
    /**
     * Metodo que crea o recupera la instancia del {@link DAOAuthGestor}. Sigue el patron Singleton.
     * @return gestorDAO
     */
    protected static DAOAuthGestor getDAOAuthgestor()
    {
        synchronized(DAOAuthGestor.class)
        {
            if (gestorAuthDAO == null) gestorAuthDAO = new DAOAuthGestor();
        }
        return gestorAuthDAO;
    }
    /**
     * Metodo que registra el usuario en la base de datos a traves de Datanucleus.
     * @param idCard
     * @param username
     * @param password
     * @param email
     * @param age
     * @param gender
     * @param occupation
     * @param admin
     * @return string (para comprobar si se ha registrado con exito el usuario o no)
     */
    public String registerUser(int idCard, String username, String password, String email, int age, String gender, String occupation, boolean admin)
	{
		DAOGestor.usersList();

			for (User aux1 : DAOGestor.users){
				if (aux1.getIdCard()==idCard) return "errorUser";
			}
				PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
				//Insert data in the DB
				PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
				Transaction transaction = persistentManager.currentTransaction();
				try {
					transaction.begin();
					User user = new User(idCard, username, password, email, age, gender, occupation, admin);
					persistentManager.makePersistent(user);
					logger.info("- Inserted into db user: " + user.getIdCard());
					transaction.commit();
				} catch (Exception ex) {
					logger.error("* Exception inserting user into db: " + ex.getMessage());
				} finally {
					if (transaction.isActive()) {
						transaction.rollback();
					}
					persistentManagerFactory.close();
					persistentManager.close();
					DAOGestor.usersList();
					return "true";
				}
	}
    /**
     * Metodo que borra el usuario de la base de datos a traves de Datanucleus.
     */
    public void deleteUser()
	{
		 try
	        {
				PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
				PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
			 	Transaction transaction = persistentManager.currentTransaction();
	            try {
	            	transaction.begin();
					User usudelete = persistentManager.getObjectById(User.class, DAOGestor.userLogged.getIdCard());
	            	persistentManager.deletePersistent(usudelete);
	            	logger.info("User deleted");
					transaction.commit();
	            } catch (Exception ex) {
	            	logger.error("* Exception deleting user from db: " + ex.getMessage());
	            } finally {
	                if (transaction.isActive()) {
	                	transaction.rollback();
	                }
	                persistentManagerFactory.close();
	                persistentManager.close();
	            }
	        }
	        catch (Exception ex)
	        {
	        	logger.error("* Exception: " + ex.getMessage());
	        }
	}
	/**
	 * Metodo que selecciona todos los usuarios de la base de datos a traves de Datanucleus.
	 */
	public void selectUsers()
	{
		PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		//Insert data in the DB
		PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
		Transaction transaction = persistentManager.currentTransaction();

		 	try {
            	transaction.begin();
            	@SuppressWarnings("unchecked")
    			Query <User> q1 = persistentManager.newQuery("SELECT FROM " + User.class.getName());
    		    for (User aux : q1.executeList()) {
					persistentManager.makeTransient(aux);
    				DAOGestor.users.add(aux);
    			}
				transaction.commit();

            } catch (Exception ex) {
            	logger.error("* Exception selecting users from db: " + ex.getMessage());
            }finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			persistentManagerFactory.close();
			persistentManager.close();
		}
		
	}
}