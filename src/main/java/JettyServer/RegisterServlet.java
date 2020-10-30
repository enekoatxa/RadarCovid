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

public class RegisterServlet extends HttpServlet {
    private static String correctRegistration = "true";

    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String occupation = request.getParameter("occupation");

        System.out.println("I have received: User: " + user + " ,Password: " + pass + " ,Email: " + email + " ,Age: " + age + " ,Gender: " + gender + " ,Occupation: " + occupation);
        //Kalkulatu erregistroa ondo egin den edo ez
        //correctRegistration=callToRegistrationManager();

        //Prepare the response and return it
        final ByteBuffer content = ByteBuffer.wrap(correctRegistration.getBytes(StandardCharsets.UTF_8));
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
