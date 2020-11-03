package DAO;

import javax.jdo.*;

import Objects.User;

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
    public boolean registerUser(int idCard, String password, String email, int age, String gender, String occupation, boolean admin)
	{
		boolean ok=false;
		User newUser = new User(idCard,password,email,age, gender, occupation, admin);
		try {
			PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
			Transaction transaction = persistentManager.currentTransaction();
			try{
				transaction.begin();
				persistentManager.makePersistent(newUser);
				transaction.commit();
				ok=true;
			}catch(Exception error2){
				ok=false;
			}finally {
				if (transaction.isActive()){
					transaction.rollback();
				}
				persistentManager.close();
			}

		}catch(Exception error1){
			System.out.println("Exception inserting data into DB.");
		}
		return ok;
	}
    public void deleteUser(String username, String password)
	{
		 try
	        {
			 	DAOGestor.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
			 	DAOGestor.persistentManager = DAOGestor.persistentManagerFactory.getPersistenceManager();
			 	DAOGestor.transaction = DAOGestor.persistentManager.currentTransaction();
	            try {
	            	DAOGestor.transaction.begin();
					Query<User> usersQuery = DAOGestor.persistentManager.newQuery("SELECT FROM USER WHERE username = '"+username+"'");

					for (User aux : usersQuery.executeList())
					{
						DAOGestor.persistentManager.deletePersistent(aux);
						break;
					}
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

	public User logIn(String userName, String password)
	{

	}
}