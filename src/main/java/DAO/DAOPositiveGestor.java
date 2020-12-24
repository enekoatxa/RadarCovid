package DAO;

import javax.jdo.*;
import Objects.Positive;
import Objects.User;

/**
 * Gestor DAO que se relaciona con la base de datos remota a traves de Datanucleus para la gestion de positivos.
 * @author Alumno
 *
 */
public class DAOPositiveGestor {
    private static DAOPositiveGestor gestorPositiveDAO = null;

    private DAOPositiveGestor() {
    }
    /**
     * Metodo que crea o recupera la instancia del {@link DAOPositiveGestor}. Sigue el patron Singleton.
     * @return gestorDAO
     */
    public static DAOPositiveGestor getDAOPositivegestor()
    {
        synchronized(DAOPositiveGestor.class)
        {
            if (gestorPositiveDAO == null) gestorPositiveDAO = new DAOPositiveGestor();
        }
        return gestorPositiveDAO;
    }
    /**
     * Metodo que registra el positivo en la base de datos a traves de Datanucleus.
     * @param latitude
     * @param longitude
     * @param year
     * @param month
     * @param day
     * @return boolean (si se ha registrado con exito o no)
     */
    protected boolean registerPositive(double latitude, double longitude, int year, int month, int day)
	{
		boolean ok = false;
				PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
				PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
			 	Transaction transaction = persistentManager.currentTransaction();
				User copy = DAOGestor.userLogged;
	            try {
	                transaction.begin();
	                DAOAuthGestor.getDAOAuthgestor().deleteUser();
	                Positive posit = new Positive(copy, latitude, longitude, year, month, day);
	                persistentManager.makePersistent(posit);
	                System.out.println("- Inserted into db positive: " + posit.getPatient().getIdCard());
	                transaction.commit();
	                ok = true;
	            } catch (Exception ex) {
	                System.err.println("* Exception inserting positive into db: " + ex.getMessage());
	                ok = false;
	            } finally {
	                if (transaction.isActive()) {
	                	transaction.rollback();
	                }
					persistentManagerFactory.close();
	                persistentManager.close();
					DAOGestor.getDAOgestor().login(copy.getIdCard(), copy.getPassword());
	                return ok;
	            }
	}
    /**
     * Metodo que selecciona los positivos de la base de datos a traves de Datanucleus.
     * @return string (lista de positivos)
     */
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