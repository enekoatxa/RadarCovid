package DAO;

import javax.jdo.*;
import Objects.User;
import Objects.Positive;
import lombok.*;

protected class DAOPositiveGestor {
    private static DAOPositiveGestor gestorPositiveDAO = null;

    private DAOPositiveGestor() {
    }

    protected static DAOPositiveGestor getDAOPositivegestor()
    {
        synchronized(DAOPositiveGestor.class)
        {
            if (gestorPositiveDAO == null) gestorPositiveDAO = new DAOPositiveGestor();
        }
        return gestorPositiveDAO;
    }
    protected void registerPositive(User patient, double latitude, double longitude, int year, int month, int day)
	{
		 try
	        {
			 	DAOGestor.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
			 	DAOGestor.persistentManager = DAOGestor.persistentManagerFactory.getPersistenceManager();
			 	DAOGestor.transaction = DAOGestor.persistentManager.currentTransaction();
	            try {
	                DAOGestor.transaction.begin();
	                Positive posit = new Positive(patient, latitude, longitude, year, month, day);
	                DAOGestor.persistentManager.makePersistent(posit);
	                System.out.println("- Inserted into db positive: " + posit.getPatient().getIdCard());
	                DAOGestor.transaction.commit();

	            } catch (Exception ex) {
	                System.err.println("* Exception inserting positive into db: " + ex.getMessage());
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
//    public void deletePositive(Positive posit)
//	{
//		 try
//	        {
//			 	DAOGestor.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
//		        //Insert data in the DB
//			 	DAOGestor.persistentManager = DAOGestor.persistentManagerFactory.getPersistenceManager();
//			 	DAOGestor.transaction = DAOGestor.persistentManager.currentTransaction();
//	            try {
//	            	DAOGestor.transaction.begin();
//	            	Positive delete = DAOGestor.persistentManager.getObjectById(Positive.class, posit.getIdPositive());
//	                DAOGestor.persistentManager.deletePersistent(delete);
//	                System.out.println("- Deleted from db positive: " + posit.getIdPositive());
//	                DAOGestor.transaction.commit();
//
//	            } catch (Exception ex) {
//	                System.err.println("* Exception deleting positive from db: " + ex.getMessage());
//	            } finally {
//	                if (DAOGestor.transaction.isActive()) {
//	                	DAOGestor.transaction.rollback();
//	                }
//	                DAOGestor.persistentManager.close();
//	            }
//	        }
//	        catch (Exception ex)
//	        {
//	            System.err.println("* Exception: " + ex.getMessage());
//	        }
//	}
}