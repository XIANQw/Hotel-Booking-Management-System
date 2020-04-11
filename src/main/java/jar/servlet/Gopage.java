package jar.servlet;

import javax.servlet.http.*;

import jar.bean.UserBean;
import jar.dao.UserDao;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.*;

public class Gopage extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
		String page = req.getParameter("page");
		if ("mainPage".equals(page)) {
            mainPage(req, resp);
		} else if ("profile".equals(page)) {
			profile(req, resp);
		} else {
			return ;
		}
    }

    public void mainPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
            req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
            return ;
    }
    public void profile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
            req.getRequestDispatcher("/static/view/profile.jsp").forward(req, resp);
            return ;
    }





}
