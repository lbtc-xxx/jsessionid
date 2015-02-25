package hoge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/test")
public class SessionServlet extends HttpServlet {

    private static final String KEY = "somekey";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();
        final HttpSession session = request.getSession();

        Integer value = (Integer) session.getAttribute(KEY);
        if (value == null) {
            value = 1;
        }

        String url = response.encodeURL("http://localhost:8080/jsessionid-1.0-SNAPSHOT/test");

        writer.write("<html>");
        writer.write("<a href=\"" + url + "\">");
        writer.write(url);
        writer.write("</a>");
        writer.write("<p>");
        writer.write("<p>isNew: " + session.isNew() + "</p>");
        writer.write("<p>" + KEY + ": " + value + "</p>");
        writer.write("</p>");
        writer.write("</html>");

        session.setAttribute(KEY, ++value);
    }
}
