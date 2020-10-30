package JettyServer;

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

public class LoginServlet extends HttpServlet {
    private static String correctLogin = "true";

    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        System.out.println("I have received: User: " + user + " Password: " + pass);

        //Kalkulatu logina ona den edo ez
        //correctLogin=callToLoginManager();

        //Prepare the response and return it
        final ByteBuffer content = ByteBuffer.wrap(correctLogin.getBytes(StandardCharsets.UTF_8));
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
