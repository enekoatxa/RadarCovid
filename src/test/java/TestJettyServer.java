import JettyServer.JettyServer;

import com.github.javatlacati.contiperf.PerfTest;
import com.github.javatlacati.contiperf.Required;
import com.github.javatlacati.contiperf.junit.ContiPerfRule;
import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestJettyServer {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestJettyServer.class);
    }

    public void startServer() throws Exception {
        JettyServer.start();
    }

    public void stopServer() throws Exception {
        JettyServer.stop();
    }

    @Before
    public void setUp() throws Exception {
        startServer();
    }

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 5)
    @Required(max = 20000, average = 10000)
    public void testLoginServlet() throws Exception{

        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/login").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),200);
        http.disconnect();

    }

    @Test
    @PerfTest(invocations = 5)
    @Required(max = 20000, average = 10000)
    public void testRegisterServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/register").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),500);
        http.disconnect();

    }

    @Test
    @PerfTest(invocations = 5)
    @Required(max = 20000, average = 10000)
    public void testPositiveAddServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/addPositive").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),500);
        http.disconnect();

    }

    @Test
    @PerfTest(invocations = 5)
    @Required(max = 30000, average = 10000)
    public void testPositivesServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/positives").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),200);
        http.disconnect();


    }

    @Test
    @PerfTest(invocations = 5)
    @Required(max = 40000, average = 20000)
    public void testStatsServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/stats").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),200);
        http.disconnect();
    }

    @After
    public final void after() throws Exception {
        stopServer();
    }
}
