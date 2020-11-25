import JettyServer.JettyServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestJettyServer {

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

    @Test
    public void testLoginServlet() throws Exception{

        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/login").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),200);
        http.disconnect();

    }

    @Test
    public void testRegisterServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/register").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),500);
        http.disconnect();

    }

    @Test
    public void testPositiveAddServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/addPositive").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),500);
        http.disconnect();

    }

    @Test
    public void testPositivesServlet() throws Exception{
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8090/positives").openConnection();
        http.connect();
        assertEquals(http.getResponseCode(),200);
        http.disconnect();


    }

    @Test
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
