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

    public int[] readPositivesByGender(){
        int[] ret = new int[3];
        ret[0]=0; ret[1]=0; ret[2]=0;
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            q1.filter("this.patient.gender == 'Male'");
            for (Positive aux : q1.executeList()) {
                ret[0]++;
            }
            q1.filter("this.patient.gender == 'Female'");
            for (Positive aux : q1.executeList()) {
                ret[1]++;
            }
            q1.filter("this.patient.gender == 'Other'");
            for (Positive aux : q1.executeList()) {
                ret[2]++;
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

    public int[] readPositivesByOccupation(){
        int[] ret = new int[5];
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            q1.filter("this.patient.occupation == 'First sector'");
            for (Positive aux : q1.executeList()) {
                ret[0]++;
            }
            q1.filter("this.patient.occupation == 'Second sector'");
            for (Positive aux : q1.executeList()) {
                ret[1]++;
            }
            q1.filter("this.patient.occupation == 'Third sector'");
            for (Positive aux : q1.executeList()) {
                ret[2]++;
            }
            q1.filter("this.patient.occupation == 'Unoccupied'");
            for (Positive aux : q1.executeList()) {
                ret[3]++;
            }
            q1.filter("this.patient.occupation == 'Student'");
            for (Positive aux : q1.executeList()) {
                ret[4]++;
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

    public int[] readPositivesByAge(){
        int[] ret = new int[100];
        for (int i=0; i<100; i++){
            ret[i]=0;
        }
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            for (Positive aux : q1.executeList()) {
                ret[aux.getPatient().age-1]++;
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

    public int[] readPositivesByTime(){
        int[] ret = new int[18];
        for (int i=0; i<18; i++){
            ret[i]=0;
        }
        PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        //Insert data in the DB
        PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
        Transaction transaction = persistentManager.currentTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("unchecked")
            Query <Positive> q1 = persistentManager.newQuery("SELECT FROM " + Positive.class.getName());
            for (Positive aux : q1.executeList()) {
                ret[((aux.getYear()%2020)*12)+aux.getMonth()-1]++;
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