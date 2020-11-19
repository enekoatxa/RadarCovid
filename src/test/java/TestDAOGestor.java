import DAO.DAOGestor;
import Objects.Positive;
import Objects.User;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDAOGestor {
    private User user1;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestDAOGestor.class);
    }

    @Before
    public void setUp() {
        user1 = new User(97, "lolito2", "1234", "landerp@opendeusto.es", 22, "M", "Student", false);
    }

    @Test
    public void testRegisterUser(){
        DAOGestor.getDAOgestor().registrarse(user1.getIdCard(), user1.getUsername(), user1.getPassword(), user1.getEmail(), user1.getAge(), user1.getGender(), user1.getOccupation(), user1.isAdmin());
        assertTrue(DAOGestor.getDAOgestor().registered);
    }

    @Test
    public void testRegisterPositive(){
        assertTrue(DAOGestor.getDAOgestor().registerPositive(user1, 100, 200, 2020, 12, 30));
    }

    @Test
    public void testLogin(){
        //DAOGestor.login(user1.getIdCard(), user1.getPassword());
        assertEquals(user1.getUsername(), DAOGestor.userLogged.getUsername());
    }

    @Test
    public void testUserDelete(){
        DAOGestor.getDAOgestor().deleteUser();
        assertEquals("", DAOGestor.userLogged.getUsername());
    }

}

