import AppService.AuthGestor;
import AppService.PositiveGestor;
import AppService.StatsGestor;
import DAO.DAOGestor;
import DAO.DAOStatsGestor;
import Objects.User;
import junit.framework.JUnit4TestAdapter;
import org.junit.After;
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
    DAOStatsGestor daoStatsGestor;

    //For StatsByGender
    int[] ret = new int[3];
    int numberOfOtherBefore;

    //For StatsByAge
    int[] ret2 = new int[100];
    int numberOfAge60Before;

    //For StatsByOccupation
    int[] ret3 = new int[5];
    int numberOfUnoccupiedBefore;

    //For StatsByTime
    int[] ret4 = new int[18];
    int numberOf20201230Before;



    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestAppService.class);
    }


    @Before
    public void setUp() {
        int idRandom = (int) Math.floor(Math.random() * (1 - 2000 + 1) + 2000);
        when(user1.getIdCard()).thenReturn(idRandom);
        when(user1.getUsername()).thenReturn("test");
        when(user1.getPassword()).thenReturn("1234");
        when(user1.getEmail()).thenReturn("landerp@opendeusto.es");
        when(user1.getAge()).thenReturn(22);
        when(user1.getGender()).thenReturn("Male");
        when(user1.getOccupation()).thenReturn("Student");
        when(user1.isAdmin()).thenReturn(false);

        statsGestor.getStatsgestor().obtainStatistics();
        daoStatsGestor =DAOStatsGestor.getDAOStatsgestor();
        ret = daoStatsGestor.getStatsGender();
        numberOfOtherBefore = ret[2];

        ret2 = daoStatsGestor.getStatsAge();
        numberOfAge60Before = ret2[59];

        ret3 = daoStatsGestor.getStatsOccupation();
        numberOfUnoccupiedBefore = ret3[3];

        ret4 = daoStatsGestor.getStatsTime();
        numberOf20201230Before = ret4[2020%2020*12+12-1];




    }



    @Test
    public void testRegisterLoginDelete(){
        assertEquals(authGestor.getGestorAuth().register(user1.getIdCard(), user1.getUsername(), user1.getPassword(), user1.getEmail(), user1.getAge(), user1.getGender(), user1.getOccupation(), user1.isAdmin()),"true");

        authGestor.getGestorAuth().logIn(user1.getIdCard(), user1.getPassword());
        assertEquals(user1.getUsername(), daoGestor.userLogged.getUsername());

        authGestor.getGestorAuth().deleteCount();
        assertEquals("",daoGestor.userLogged.getUsername());
    }


    @Test
    public void testRegisterPositive(){
        User posit = new User((int) Math.floor(Math.random() * (1 - 2000 + 1) + 2000), "test", "1234", "landerp@opendeusto.es", 60, "Other", "Unoccupied", false);
        authGestor.getGestorAuth().register(posit.getIdCard(), posit.getUsername(), posit.getPassword(), posit.getEmail(), posit.getAge(), posit.getGender(), posit.getOccupation(), posit.isAdmin());
        authGestor.getGestorAuth().logIn(posit.getIdCard(), posit.getPassword());
        assertTrue(positiveGestor.getPositivegestor().registerPositive(100, 200, 2020, 12, 30));
    }


    @Test
    public void testStatsByGender(){
        ret = daoStatsGestor.getStatsGender();
        int numberOfOtherAfter = ret[2];

        assertEquals(numberOfOtherAfter, numberOfOtherBefore++);
    }

    @Test
    public void testStatsByAge(){
        ret2 = daoStatsGestor.getStatsAge();
        int numberOfAge60After = ret2[59];

        assertEquals(numberOfAge60After,numberOfAge60Before++);
    }

    @Test
    public void testStatsByOccupation(){
        ret3 = daoStatsGestor.getStatsOccupation();
        int numberOfUnoccupiedAfter = ret3[3];

        assertEquals(numberOfUnoccupiedAfter, numberOfUnoccupiedBefore++);
    }

    @Test
    public void testStatsByTime(){
        ret4 = daoStatsGestor.getStatsTime();
        int numberOf20201230After = ret4[2020%2020*12+12-1];

        assertEquals(numberOf20201230After, numberOf20201230Before++);
    }

    @After
    public void after(){
        ret=null;
        ret2=null;
        ret3=null;
        ret4=null;
    }

}
