import AppService.AuthGestor;
import AppService.PositiveGestor;
import AppService.StatsGestor;
import DAO.DAOGestor;
import DAO.DAOStatsGestor;
import Objects.Positive;
import Objects.User;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestAppService {

    @Mock
    User user1;

    DAOGestor daoGestor;
    AuthGestor authGestor;
    PositiveGestor positiveGestor;
    StatsGestor statsGestor;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestAppService.class);
    }

    @Before
    public void setUp() {
        int idRandom = (int) Math.floor(Math.random() * (1 - 200 + 1) + 200);
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
    public void testRegisterLoginDelete(){
        assertTrue(authGestor.getGestorAuth().register(user1.getIdCard(), user1.getUsername(), user1.getPassword(), user1.getEmail(), user1.getAge(), user1.getGender(), user1.getOccupation(), user1.isAdmin()));

        authGestor.getGestorAuth().logIn(user1.getIdCard(), user1.getPassword());
        assertEquals(user1.getUsername(), daoGestor.userLogged.getUsername());

        authGestor.getGestorAuth().deleteCount();
        assertEquals("",daoGestor.userLogged.getUsername());
    }

    /*
    @Test
    public void testRegisterPositive(){
        User posit = new User((int) Math.floor(Math.random() * (1 - 200 + 1) + 200), "test", "1234", "landerp@opendeusto.es", 22, "Male", "Student", false);
        assertTrue(positiveGestor.getPositivegestor().registerPositive(posit, 100, 200, 2020, 12, 30));
    }

    @Test
    public void testStatsByGender(){
        int[] ret = statsGestor.getStatsgestor().statsByGender();
        assertTrue(ret[0]>0);
    }

    @Test
    public void testStatsByAge(){
        int[] ret = statsGestor.getStatsgestor().statsByAge();
        assertTrue(ret[0]==9);
    }

    @Test
    public void testStatsByOccupation(){
        int[] ret = statsGestor.getStatsgestor().statsByOccupation();
        assertTrue(ret[1]>0);
    }

    @Test
    public void testStatsByTime(){
        int[] ret = statsGestor.getStatsgestor().statsByTime();
        assertTrue(ret[0]==3);
    }

     */


}
