import DAO.DAOGestor;
import Objects.User;
import junit.framework.JUnit4TestAdapter;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class TestDAO {



    @Mock
    User user1;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestDAO.class);
    }


    @Before
    public void setUp() {
        int idRandom = (int) Math.floor(Math.random()*(1-200+1)+200);
        when(user1.getIdCard()).thenReturn(idRandom);
        when(user1.getUsername()).thenReturn("test");
        when(user1.getPassword()).thenReturn("1234");
        when(user1.getEmail()).thenReturn("landerp@opendeusto.es");
        when(user1.getAge()).thenReturn(22);
        when(user1.getGender()).thenReturn("Male");
        when(user1.getOccupation()).thenReturn("Student");
        when(user1.isAdmin()).thenReturn(false);
    }


    @Test
    public void testRegisterLoginDeleteUser() throws InterruptedException {
        DAOGestor.getDAOgestor().registrarse(user1.getIdCard(), user1.getUsername(), user1.getPassword(), user1.getEmail(), user1.getAge(), user1.getGender(), user1.getOccupation(), user1.isAdmin());
        assertTrue(DAOGestor.getDAOgestor().registered);
        DAOGestor.getDAOgestor().login(user1.getIdCard(), user1.getPassword());
        assertEquals(user1.getUsername(), DAOGestor.userLogged.getUsername());
        DAOGestor.getDAOgestor().deleteUser();
        assertEquals("", DAOGestor.userLogged.getUsername());
        Thread.sleep(200);
    }

    /*
    @Test
    public void testRegisterPositive(){
        User positivo = new User (999, user1.getUsername(), user1.getPassword(), user1.getEmail(), user1.getAge(), user1.getGender(), user1.getOccupation(), user1.isAdmin());
        assertTrue(DAOGestor.getDAOgestor().registerPositive(positivo, 100, 200, 2020, 12, 30));
    }

     */

    @After
    public final void after(){
        user1=null;
    }

}

