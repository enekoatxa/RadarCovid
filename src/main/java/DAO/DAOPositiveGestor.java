package DAO;

import javax.jdo.*;
import Objects.User;
import Objects.Positive;
import lombok.*;

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
    public void registerPositive(User patient, double latitude, double longitude, int year, int month, int day)
	{
		 try
	        {
			 	DAOGestor.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		        //Insert data in the DB
			 	DAOGestor.persistentManager = DAOGestor.persistentManagerFactory.getPersistenceManager();
			 	DAOGestor.transaction = DAOGestor.persistentManager.currentTransaction();
	            try {
	            	System.out.println("1");
	                DAOGestor.transaction.begin();
	                System.out.println("1");
	                Positive posit = new Positive(patient, latitude, longitude, year, month, day);
	                System.out.println("1");
	                DAOGestor.persistentManager.makePersistent(posit);
	                System.out.println("1");
	                System.out.println("- Inserted into db positive: " + posit.getPatient().getIdCard());
	                DAOGestor.transaction.commit();

	            } catch (Exception ex) {
	                System.err.println("* Exception inserting data into db: " + ex.getMessage());
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