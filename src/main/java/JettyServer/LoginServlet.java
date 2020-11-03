package JettyServer;

import AppService.AuthGestor;
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

public class LoginServlet extends HttpServlet {
    private static String correctLogin = "true";
    private User userLogged = null;

    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        System.out.println("I have received: User: " + user + " Password: " + pass);

        userLogged = AuthGestor.getGestorAuth().logIn(user, pass);

        String userJsonString = new Gson().toJson(userLogged);
        //Prepare the response and return it
        final ByteBuffer content = ByteBuffer.wrap(userJsonString.getBytes(StandardCharsets.UTF_8));
        final AsyncContext async = request.startAsync();
        final ServletOutputStream out = response.getOutputStream();
        out.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
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
