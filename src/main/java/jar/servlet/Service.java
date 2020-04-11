package jar.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class Service extends HttpServlet {
    public void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException { 
		String method = req.getParameter("method");
		if ("Login".equals(method)) {
			req.getRequestDispatcher("Login").forward(req, resp);
		} else if ("Signup".equals(method)) {
			req.getRequestDispatcher("Signup").forward(req, resp);
		} else if ("Logout".equals(method)) {
			req.getRequestDispatcher("Logout").forward(req, resp);
		} else {
			return ;
		} 
	}
}