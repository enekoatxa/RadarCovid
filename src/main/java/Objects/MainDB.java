package Objects;

import javax.jdo.*;
import Objects.User;
import Objects.Positive;
import lombok.*;

// Class to test Datanucleus
public class MainDB {
    public static void main(String[] args)
    {
        /*
         * USER
         * - idCard
         * - password
         * - email
         * - age
         * - gender
         * - occupation
         * - admin
         * POSITIVE
         * - idPositive
         * - user
         * - latitude
         * - longitude
         * - year
         * - month
         * - day
         */

        try
        {
            PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
            //Insert data in the DB
            PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();
            Transaction transaction = persistentManager.currentTransaction();
            try {
                transaction.begin();
                User user1 = new User(72839127, "1234", "juansolozabal@gmail.com", 22, "M", "Student", true);
                Positive posit1 = new Positive(user1, 100, 200, 2020, 11, 01);
                persistentManager.makePersistent(user1);
                System.out.println("- Inserted into db user: " + user1.getIdCard());
                persistentManager.makePersistent(posit1);
                System.out.println("- Inserted into db positive: " + posit1.getPatient().getIdCard());
                transaction.commit();

            } catch (Exception ex) {
                System.err.println("* Exception inserting data into db: " + ex.getMessage());
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                persistentManager.close();
            }
        }
        catch (Exception ex)
        {
            System.err.println("* Exception: " + ex.getMessage());
        }

    }
}
