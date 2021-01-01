package DAO;

import Objects.Positive;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.jdo.*;

/**
 * Gestor DAO que se relaciona con la base de datos remota a traves de Datanucleus para la gestion de estadisticas.
 * @author Alumno
 *
 */
public class DAOStatsGestor {
    private static DAOStatsGestor gestorStatsDAO = null;
    @Getter
    private int[] statsGender = new int[3];
    @Getter
    private int[] statsOccupation = new int[5];
    @Getter
    private int[] statsAge = new int[100];
    @Getter
    private int[] statsTime = new int[18];
    static Logger logger = Logger.getLogger(DAOStatsGestor.class.getName());
    private DAOStatsGestor() {
        for (int i = 0; i < statsGender.length; i++) {
            statsGender[i]=0;
        }
        for (int i = 0; i < statsOccupation.length; i++) {
            statsOccupation[i]=0;
        }
        for (int i = 0; i < statsAge.length; i++) {
            statsAge[i]=0;
        }
        for (int i = 0; i < statsTime.length; i++) {
            statsTime[i]=0;
        }
    }
    /**
     * Metodo que crea o recupera la instancia del {@link DAOStatsGestor}. Sigue el patron Singleton.
     * @return gestorDAO
     */
    public static DAOStatsGestor getDAOStatsgestor()
    {
        synchronized(DAOStatsGestor.class)
        {
            if (gestorStatsDAO == null) gestorStatsDAO = new DAOStatsGestor();
        }
        return gestorStatsDAO;
    }
    /**
     * Metodo que lee todas las estadisticas de la base de datos a traves de Datanucleus.
     */
    public void readAllStats(){
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            for (Positive aux : q1.executeList()) {
                statsAge[aux.getPatient().age-1]++;
                statsTime[((aux.getYear()%2020)*12)+aux.getMonth()-1]++;
            }
            q1.filter("this.patient.gender == 'Male'");
            for (Positive aux : q1.executeList()) {
                statsGender[0]++;
            }
            q1.filter("this.patient.gender == 'Female'");
            for (Positive aux : q1.executeList()) {
                statsGender[1]++;
            }
            q1.filter("this.patient.gender == 'Other'");
            for (Positive aux : q1.executeList()) {
                statsGender[2]++;
            }
            q1.filter("this.patient.occupation == 'First sector'");
            for (Positive aux : q1.executeList()) {
                statsOccupation[0]++;
            }
            q1.filter("this.patient.occupation == 'Second sector'");
            for (Positive aux : q1.executeList()) {
                statsOccupation[1]++;
            }
            q1.filter("this.patient.occupation == 'Third sector'");
            for (Positive aux : q1.executeList()) {
                statsOccupation[2]++;
            }
            q1.filter("this.patient.occupation == 'Unoccupied'");
            for (Positive aux : q1.executeList()) {
                statsOccupation[3]++;
            }
            q1.filter("this.patient.occupation == 'Student'");
            for (Positive aux : q1.executeList()) {
                statsOccupation[4]++;
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
        }
    }
}