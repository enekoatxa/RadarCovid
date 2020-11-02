package DAO;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import Objects.User;

public class DAOAuthGestor {
    private static DAOAuthGestor gestorAuthDAO = null;

    private DAOAuthGestor() {
    }

    public static DAOAuthGestor getDAOAuthgestor()
    {
        synchronized(DAOAuthGestor.class)
        {
            if (gestorAuthDAO == null) gestorAuthDAO = new DAOAuthGestor();
        }
        return gestorAuthDAO;
    }
    public void registerUser(int idCard, String password, String email, int age, String gender, String occupation, boolean admin)
	{
		 try
	        {
			 	DAOGestor.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
			 	DAOGestor.persistentManager = DAOGestor.persistentManagerFactory.getPersistenceManager();
			 	DAOGestor.transaction = DAOGestor.persistentManager.currentTransaction();
	            try {
	            	DAOGestor.transaction.begin();
	                User user = new User(idCard, password, email, age, gender, occupation, admin);
	                DAOGestor.persistentManager.makePersistent(user);
	                System.out.println("- Inserted into db user: " + user.getIdCard());
	                DAOGestor.transaction.commit();

	            } catch (Exception ex) {
	                System.err.println("* Exception inserting user into db: " + ex.getMessage());
	            } finally {
	                if (DAOGestor.transaction.isActive()) {
	                	DAOGestor.transaction.rollback();
	                }
	                DAOGestor.persistentManager.close();
	            }
	        }
	        catch (Exception ex)
	        {
	            System.err.println("* Exception: " + ex.getMessage());
	        }
	}
    public void deleteUser(User user)
	{
		 try
	        {
			 	DAOGestor.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
			 	DAOGestor.persistentManager = DAOGestor.persistentManagerFactory.getPersistenceManager();
			 	DAOGestor.transaction = DAOGestor.persistentManager.currentTransaction();
	            try {
	            	DAOGestor.transaction.begin();
	                User delete = DAOGestor.persistentManager.getObjectById(User.class, user.getIdCard());
	                DAOGestor.persistentManager.deletePersistent(delete);
	                System.out.println("- Deleted from db user: " + user.getIdCard());
	                DAOGestor.transaction.commit();

	            } catch (Exception ex) {
	                System.err.println("* Exception deleting user from db: " + ex.getMessage());
	            } finally {
	                if (DAOGestor.transaction.isActive()) {
	                	DAOGestor.transaction.rollback();
	                }
	                DAOGestor.persistentManager.close();
	            }
	        }
	        catch (Exception ex)
	        {
	            System.err.println("* Exception: " + ex.getMessage());
	        }
	}
}