package DAO;


import Objects.Positive;
import Objects.User;

import javax.jdo.*;
import java.util.ArrayList;

public class DAOStatsGestor {
    private static DAOStatsGestor gestorStatsDAO = null;

    private DAOStatsGestor() {
    }

    public static DAOStatsGestor getDAOStatsgestor()
    {
        synchronized(DAOStatsGestor.class)
        {
            if (gestorStatsDAO == null) gestorStatsDAO = new DAOStatsGestor();
        }
        return gestorStatsDAO;
    }

    public ArrayList<Positive> readPositivesByGender(String gender){
        ArrayList<Positive> ret = new ArrayList<Positive>();
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            for (Positive aux : q1.executeList()) {
                ret.add(aux);
            }
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

    public ArrayList<Positive> readPositivesByOccupation(String occupation){
        ArrayList<Positive> ret = new ArrayList<Positive>();
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            for (Positive aux : q1.executeList()) {
                ret.add(aux);
            }
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


//   Query<Positive> q1 = DAOGestor.persistentManager.newQuery("SELECT p.* FROM " + Positive.class.getName() + " p, " + User.class.getName() +
//         " u WHERE u.idCard=p.idCard AND u.occupation='"+ occupation +"';");
//   Query<Positive> q1 = DAOGestor.persistentManager.newQuery("SELECT p.* FROM " + Positive.class.getName() + " p, " + User.class.getName() +
//         " u WHERE u.idCard=p.idCard AND u.gender='"+ gender +"';");

//select COUNT(*), YEAR, MONTH from POSITIVE group by YEAR, MONTH;
// SELECT COUNT(*), USER.AGE FROM POSITIVE, USER WHERE USER.IDCARD=POSITIVE.IDCARD group by USER.AGE;
