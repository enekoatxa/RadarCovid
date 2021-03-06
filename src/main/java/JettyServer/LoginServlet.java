package JettyServer;

import AppService.AuthGestor;
import DAO.DAOAuthGestor;
import DAO.DAOGestor;
import Objects.User;
import com.google.gson.Gson;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static java.lang.Integer.parseInt;
/**
 * Servlet asociado al {@link JettyServer} relacionado con el logIn del usuario.
 * @author Alumno
 *
 */
public class LoginServlet extends HttpServlet {
    private User userLogged = null;
    /**
     * Peticion GET que comprueba el logIn de un usuario. Delega la funcion en el {@link AuthGestor#logIn(int, String)}
     */
    static Logger logger = Logger.getLogger(LoginServlet.class.getName());
    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String idCard = request.getParameter("idCard");
        String pass = request.getParameter("pass");
        logger.info("I have received: User: " + idCard + " Password: " + pass);
        String responseString ="";
        try{
            responseString = AuthGestor.getGestorAuth().logIn(Integer.parseInt(idCard), pass);
        } catch (NumberFormatException e){
            responseString = "errorNumber";
        }

        //Prepare the response and return it
        final ByteBuffer content = ByteBuffer.wrap(responseString.getBytes(StandardCharsets.UTF_8));
        final AsyncContext async = request.startAsync();
        final ServletOutputStream out = response.getOutputStream();
        out.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
                response.addHeader("Access-Control-Allow-Origin", "*");
                while (out.isReady()) {
                    if (!content.hasRemaining()) {
                        response.setStatus(200);
                        async.complete();
                        return;
                    }
                    out.write(content.get());
                }
            }
            @Override
            public void onError(Throwable t) {
                getServletContext().log("Async Error", t);
                async.complete();
            }
        });
    }
}
