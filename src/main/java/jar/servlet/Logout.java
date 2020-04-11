package jar.servlet;

import javax.servlet.http.*;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Logout")
public class Logout extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        if(session != null){
            session.removeAttribute("user");
        }
        resp.sendRedirect("http://localhost:8080/microproject/static/view/accueil.jsp");
        return ;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    } 
}