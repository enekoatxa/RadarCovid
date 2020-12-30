package JettyServer;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
/**
 * Servidor Jetty para poder asociar el HTML y poder recoger las peticiones HTTP que realice el usuario.
 * @author Alumno
 *
 */
public class JettyServer {

    private static Server server;
    /**
     * Metodo en el que se inicia la aplicacion. Lanza el metodo {@link JettyServer#start()}
     * @param args
     */
    public static void main (String[]args){
        JettyServer server = new JettyServer();
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo que lanza el servidor Jetty.
     * @throws Exception
     */
    public static void start() throws Exception {

        int maxThreads = 100;
        int minThreads = 1;
        int idleTimeout = 120;

        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[] { connector });

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(LoginServlet.class, "/login");
        servletHandler.addServletWithMapping(RegisterServlet.class, "/register");
        servletHandler.addServletWithMapping(PositiveAddServlet.class, "/addPositive");
        servletHandler.addServletWithMapping(StatsServlet.class, "/stats");
        servletHandler.addServletWithMapping(PositivesServlet.class, "/positives");
        servletHandler.addServletWithMapping(AlertServlet.class, "/alertingSystem");

        server.start();
    }
    /**
     * Metodo que para el servidor Jetty.
     * @throws Exception
     */
    public static void stop() throws Exception {
        server.stop();
    }
}