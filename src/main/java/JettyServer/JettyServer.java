package JettyServer;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JettyServer {

    private static Server server;

    public static void main (String[]args){
        JettyServer server = new JettyServer();
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        server.start();
    }

    public static void stop() throws Exception {
        server.stop();
    }
}