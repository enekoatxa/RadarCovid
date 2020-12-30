package JettyServer;

import AppService.PositiveGestor;

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

public class AlertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String ret="";
        double longitude=0.0;
        double latitude=0.0;

        try {
            longitude = Double.parseDouble(request.getParameter("longitude"));
            latitude = Double.parseDouble(request.getParameter("latitude"));
        } catch (NumberFormatException ex){
            ret="errorNumber";
        }
        System.out.println("I have received: Longitude: " + longitude + " ,Latitude: " + latitude);
        try {
            ret = Boolean.toString(PositiveGestor.getPositivegestor().alarmSystem(latitude, longitude));
        } catch (Exception e){
            e.printStackTrace();
            ret="false";
        }
        //Prepare the response and return it
        final ByteBuffer content = ByteBuffer.wrap(ret.getBytes(StandardCharsets.UTF_8));
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
