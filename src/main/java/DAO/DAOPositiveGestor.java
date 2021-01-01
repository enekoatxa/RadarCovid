package DAO;

import javax.jdo.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Objects.EmailSenderService;
import Objects.Positive;
import Objects.User;

/**
 * Gestor DAO que se relaciona con la base de datos remota a traves de Datanucleus para la gestion de positivos.
 * @author Alumno
 *
 */
public class DAOPositiveGestor {
    private static DAOPositiveGestor gestorPositiveDAO = null;
    static Logger logger = Logger.getLogger(DAOPositiveGestor.class.getName());
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
	                logger.info("- Inserted into db positive: " + posit.getPatient().getIdCard());
	                transaction.commit();
	                ok = true;
	            } catch (Exception ex) {
	            	logger.error("* Exception inserting positive into db: " + ex.getMessage());
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
            	logger.error("* Exception selecting positives from db: " + ex.getMessage());
            } finally {
                if (transaction.isActive()) {
                	transaction.rollback();
                }
                persistentManager.close();
                persistentManagerFactory.close();
            }
		return ret;
	}

	/**
	 * Metodo para enviar una alarma, via email y navegador, al usuario en caso de que se encuentre en una zona con alta incidencia de COVID-19.
	 * @param latitude
	 * @param longitude
	 * @return boolean (si esta en una zona con riesgo o no)
	 */

	public boolean alarm(double latitude, double longitude){
    	int contadorRiesgo = 0;
    	double radioTierra = 6371; //distancia en kilometros
		double dLat = 0;
		double dLng = 0;
		double sindLat = 0;
		double sindLng = 0;
		double va1 = 0;
		double va2 = 0;
		double distancia = 0;
		double radioPeligro = 5;

		PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
		Transaction transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();
			@SuppressWarnings("unchecked")
			Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
			for (Positive aux : q1.executeList()) {
				dLat = Math.toRadians(aux.getLatitude() - latitude);
				dLng = Math.toRadians(aux.getLongitude() - longitude);
				sindLat = Math.sin(dLat/2);
				sindLng = Math.sin(dLng/2);
				va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(aux.getLatitude()));
				va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
				distancia = radioTierra * va2;
				if (distancia < radioPeligro) contadorRiesgo = contadorRiesgo+1;
			}
			transaction.commit();
		} catch (Exception ex) {
			logger.error("* Exception selecting positives from db: " + ex.getMessage());
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			persistentManager.close();
			persistentManagerFactory.close();

			if (contadorRiesgo>3){
				EmailSenderService notification = new EmailSenderService();
				notification.enviarConGMail("Riesgo zona con alta incidencia Covid-19", "Le avisamos que se encuentra en una zona con alta incidencia de casos en Covid-19. Especificamente, a su alrededor (en un radio de "+ radioPeligro +" km) hay el siguiente número de casos:"+contadorRiesgo+"\nAconsejamos visitar el siguiente enlace para mayor informacion:https://www.mscbs.gob.es/profesionales/saludPublica/ccayes/alertasActual/nCov/FAQ/FAQ4.htm");
				return true;
			}else{
				return  false;
			}
		}

	}
}