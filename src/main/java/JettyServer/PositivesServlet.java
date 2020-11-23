package JettyServer;

import AppService.PositiveGestor;
import AppService.StatsGestor;
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
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PositivesServlet extends HttpServlet {
    private static String correctLogin = "true";

    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        System.out.println("I have received a petition.");

        String positivesJsonString = new Gson().toJson(PositiveGestor.getPositivegestor().searchPositives());
        final ByteBuffer content = ByteBuffer.wrap(positivesJsonString.getBytes(StandardCharsets.UTF_8));
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