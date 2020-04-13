package jar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import jar.bean.*;
import jar.dao.RessourceDao;

public class Service extends HttpServlet {
    public void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req,
<<<<<<< HEAD
			HttpServletResponse resp) throws ServletException, IOException { 
=======
			HttpServletResponse resp) throws ServletException, IOException {
>>>>>>> wang
		String method = req.getParameter("method");
		if ("createSearch".equals(method)){
			createSearch(req, resp);
		}else if ("getCommandes".equals(method)) {
<<<<<<< HEAD
			
=======

>>>>>>> wang
		} else if ("getRessources".equals(method)){
			getRessource(req, resp);
		} else if ("createRessource".equals(method)){
			createRessource(req, resp);
		}
		else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "undefine " + method);
			req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
		}
	}

<<<<<<< HEAD
	private void createSearch(HttpServletRequest req, 
=======
	private void createSearch(HttpServletRequest req,
>>>>>>> wang
	HttpServletResponse resp) throws ServletException, IOException{
		String destination = req.getParameter("destination");
		String checkin = req.getParameter("checkin");
		String checkout = req.getParameter("checkout");
		String numPeople = req.getParameter("nb");
		String type = req.getParameter("type");
		String smoker = req.getParameter("smoker");
		String info = destination + " " +
<<<<<<< HEAD
			checkin + " " + 
=======
			checkin + " " +
>>>>>>> wang
			checkout + " " +
			numPeople + " " +
			type + " " +
			smoker;
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
	}

	private void getRessource(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		if(!Client.sessionValide(req, resp)){;
			req.getRequestDispatcher("Gopage?page=accueil").forward(req, resp);
		}
		int owner = ((UserBean)req.getSession().getAttribute("user")).getId();
		List<RessourceBean> ressources = RessourceDao.getRessourcesFromOwner(owner);
		req.setAttribute("ressources", ressources);
		req.getRequestDispatcher("/static/view/ressources.jsp").forward(req, resp);
	}

<<<<<<< HEAD
	private void createRessource(HttpServletRequest req, 
=======
	private void createRessource(HttpServletRequest req,
>>>>>>> wang
	HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		if(!Client.sessionValide(req, resp)){;
			req.getRequestDispatcher("Gopage?page=accueil").forward(req, resp);
		}
		String type = req.getParameter("type");
		float price = Float.parseFloat(req.getParameter("price"));
		int number = Integer.parseInt(req.getParameter("number"));
		String street = req.getParameter("street");
		int postal = Integer.parseInt(req.getParameter("postal"));
		String city = req.getParameter("city");
		int persons;
		if("room".equals(type)) persons = Integer.parseInt(req.getParameter("persons_room"));
		else persons = Integer.parseInt(req.getParameter("persons_house"));
		String smoker = req.getParameter("smoker");
		int owner = ((UserBean)session.getAttribute("user")).getId();
		RessourceBean ress = new RessourceBean();
<<<<<<< HEAD
		ress.setType(type); 
		ress.setPrice(price); 
		ress.setNumber(number); 
		ress.setStreet(street); 
=======
		ress.setType(type);
		ress.setPrice(price);
		ress.setNumber(number);
		ress.setStreet(street);
>>>>>>> wang
		ress.setPostal(postal);
		ress.setCity(city);
		ress.setPersons(persons);
		ress.setSmoker(smoker);
		String info = ress.getType() + " " +
<<<<<<< HEAD
			ress.getPrice() + " " + 
=======
			ress.getPrice() + " " +
>>>>>>> wang
			ress.getNumber() + " " +
			ress.getStreet() + " " +
			ress.getPostal() + " " +
			ress.getCity() + " " +
			ress.getPersons() + " " +
			ress.getSmoker() + " " +
			owner;
		RessourceDao.saveRessource(owner, ress);
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.getRequestDispatcher("Service?method=getRessources").forward(req, resp);
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> wang
