import Objects.Positive;
import Objects.User;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestObjects {

    User user1 = new User(1, "aa", "1234", "aa@gmail.com", 22, "Male", "Student", false);
    Positive positive1 = new Positive(user1, 11, 11, 2020, 11, 23);


    @Test
    public void testUser(){

        assertEquals(1, user1.getIdCard());
        assertEquals("aa", user1.getUsername());
        assertEquals("1234", user1.getPassword());
        assertEquals("aa@gmail.com", user1.getEmail());
        assertEquals(22, user1.getAge());
        assertEquals("Male", user1.getGender());
        assertEquals("Student", user1.getOccupation());
        assertFalse(user1.isAdmin());

    }

    @Test
    public void testPositive(){
        assertEquals(user1.getIdCard(), positive1.getPatient().getIdCard());
        assertEquals(11, positive1.getLatitude(), 10);
        assertEquals(11, positive1.getLongitude(), 10);
        assertEquals(2020, positive1.getYear());
        assertEquals(11, positive1.getMonth());
        assertEquals(23, positive1.getDay());
    }

    @After
    public void after(){
        user1=null;
        positive1=null;

    }
}
