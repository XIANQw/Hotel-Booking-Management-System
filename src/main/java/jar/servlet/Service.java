package jar.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import jar.bean.*;

public class Service extends HttpServlet {
    public void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException { 
		String method = req.getParameter("method");
		if ("createSearch".equals(method)){
			createSearch(req, resp);
		}else if ("getCommandes".equals(method)) {
			
		} else if ("getRessources".equals(method)){
			req.getRequestDispatcher("/static/view/ressources.jsp").forward(req, resp);
		} else if ("createRessource".equals(method)){
			createRessource(req, resp);
		}
		else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "undefine " + method);
			req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
		}
	}

	private void createSearch(HttpServletRequest req, 
	HttpServletResponse resp) throws ServletException, IOException{
		String destination = req.getParameter("destination");
		String checkin = req.getParameter("checkin");
		String checkout = req.getParameter("checkout");
		String numPeople = req.getParameter("nb");
		String type = req.getParameter("type");
		String smoker = req.getParameter("smoker");
		String info = destination + " " +
			checkin + " " + 
			checkout + " " +
			numPeople + " " +
			type + " " +
			smoker;
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
	}

	private void createRessource(HttpServletRequest req, 
	HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("user")==null){
			String info = "Session is invalid, reconnect please";
			req.setAttribute("info", info);
			req.setAttribute("type", "warning");
			req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
		}
		String type = req.getParameter("type");
		String price = req.getParameter("price");
		String number = req.getParameter("number");
		String street = req.getParameter("street");
		String postal = req.getParameter("postal");
		String city = req.getParameter("city");
		String smoker = req.getParameter("smoker");
		int owner = ((UserBean)session.getAttribute("user")).getId();
		String info = type + " " +
			price + " " + 
			number + " " +
			street + " " +
			postal + " " +
			city + " " +
			smoker + " " +
			owner;
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.getRequestDispatcher("Service?method=getRessources&id="+owner).forward(req, resp);
	}

}