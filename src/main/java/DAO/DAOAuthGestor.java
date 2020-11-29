package DAO;

import javax.jdo.*;

import Objects.Positive;
import Objects.User;

import java.time.Clock;

public class DAOAuthGestor {
    private static DAOAuthGestor gestorAuthDAO = null;

    private DAOAuthGestor() {
    }

    protected static DAOAuthGestor getDAOAuthgestor()
    {
        synchronized(DAOAuthGestor.class)
        {
            if (gestorAuthDAO == null) gestorAuthDAO = new DAOAuthGestor();
        }
        return gestorAuthDAO;
    }
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
					System.out.println("- Inserted into db user: " + user.getIdCard());
					transaction.commit();
				} catch (Exception ex) {
					System.err.println("* Exception inserting user into db: " + ex.getMessage());
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
	            	System.out.println("User deleted");
					transaction.commit();
	            } catch (Exception ex) {
	                System.err.println("* Exception deleting user from db: " + ex.getMessage());
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
	            System.err.println("* Exception: " + ex.getMessage());
	        }
	}
	
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
                System.err.println("* Exception selecting users from db: " + ex.getMessage());
            }finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			persistentManagerFactory.close();
			persistentManager.close();
		}
		
	}
}