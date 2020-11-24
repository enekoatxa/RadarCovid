package JettyServer;

import AppService.AuthGestor;
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

import static java.lang.Integer.parseInt;

public class LoginServlet extends HttpServlet {
    private User userLogged = null;

    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String idCard = request.getParameter("idCard");
        String pass = request.getParameter("pass");
        System.out.println("I have received: User: " + idCard + " Password: " + pass);
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
