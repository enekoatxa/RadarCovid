package JettyServer;

import AppService.AuthGestor;
import DAO.DAOAuthGestor;

import com.google.gson.Gson;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * Servlet asociado al {@link JettyServer} relacionado con el registro de usuario.
 * @author Alumno
 *
 */
public class RegisterServlet extends HttpServlet {
	/**
     * Peticion GET que realiza el registro de un usuario. Delega la funcion en el {@link AuthGestor#register(int, String, String, String, int, String, String, boolean)}
     */
	static Logger logger = Logger.getLogger(RegisterServlet.class.getName());
    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String idCard = request.getParameter("idCard");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String occupation = request.getParameter("occupation");
        String responseString="";
        try{
            responseString = AuthGestor.getGestorAuth().register(Integer.parseInt(idCard), user, pass, email, age, gender, occupation, false);
        } catch (NumberFormatException ex){
            responseString = "errorNumber";
        }
        logger.info("I have received: User: " + user + " ,Password: " + pass + " ,Email: " + email + " ,Age: " + age + " ,Gender: " + gender + " ,Occupation: " + occupation);

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
