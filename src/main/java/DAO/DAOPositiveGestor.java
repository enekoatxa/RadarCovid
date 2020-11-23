package DAO;

import javax.jdo.*;
import Objects.User;
import Objects.Positive;

public class DAOPositiveGestor {
    private static DAOPositiveGestor gestorPositiveDAO = null;

    private DAOPositiveGestor() {
    }

    public static DAOPositiveGestor getDAOPositivegestor()
    {
        synchronized(DAOPositiveGestor.class)
        {
            if (gestorPositiveDAO == null) gestorPositiveDAO = new DAOPositiveGestor();
        }
        return gestorPositiveDAO;
    }
    protected void registerPositive(int patientId, double latitude, double longitude, int year, int month, int day)
	{

				PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
				PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
			 	Transaction transaction = persistentManager.currentTransaction();
	            try {
	                transaction.begin();
	                Positive posit = new Positive(patientId, latitude, longitude, year, month, day);
	                persistentManager.makePersistent(posit);
	                System.out.println("- Inserted into db positive: " + posit.getPatientId());
	                transaction.commit();

	            } catch (Exception ex) {
	                System.err.println("* Exception inserting positive into db: " + ex.getMessage());
	            } finally {
	                if (transaction.isActive()) {
	                	transaction.rollback();
	                }
					persistentManagerFactory.close();
	                persistentManager.close();
	            }
	}

    public String selectPositives()
	{
			String ret = "";
			PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
			Transaction transaction = persistentManager.currentTransaction();
		 	try {
            	transaction.begin();
            	@SuppressWarnings("unchecked")
    			Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
    		    for (Positive aux : q1.executeList()) {
					ret += aux.getLatitude() + "," + aux.getLongitude() + ";";
    			}
    		    ret=ret.substring(0, ret.length()-1);
    		    transaction.commit();

            } catch (Exception ex) {
                System.err.println("* Exception selecting positives from db: " + ex.getMessage());
            } finally {
                if (transaction.isActive()) {
                	transaction.rollback();
                }
                persistentManager.close();
                persistentManagerFactory.close();
            }
		return ret;
	}
}