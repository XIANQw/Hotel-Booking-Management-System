package jar.servlet;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
public class Wheather {
 
    static String res = "";

    public Wheather() {
    }
  
    public static void sendData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("call sendData ok----------------------------------------------------------------");

        String GET_WHEATHER_URL = "http://api.weatherstack.com/current?access_key=1cf9c55f6d6b3b05c262f5d475490c8b&query=";
        String city = req.getParameter("dataCity");
        System.out.println(city);

        String query = URLEncoder.encode(city, StandardCharsets.UTF_8);
        //System.out.println(query);

        String urlInit = GET_WHEATHER_URL + query;
        URL url;
        try {
            // get URL content

            url = new URL(urlInit);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                    //System.out.println(inputLine);
                    res += inputLine;
            }
            br.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    

        // System.out.println(result);

         resp.getWriter().write(res);

        System.out.println("ok");
    }
}
