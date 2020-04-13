package jar.servlet;

import java.io.IOException;
import java.util.HashMap;
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
			HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if ("createSearch".equals(method)){
			Service.createSearch(req, resp);
		} else if ("getRessources".equals(method)) {
			Service.getRessource(req, resp);
		} else if ("createRessource".equals(method)) {
			Service.createRessource(req, resp);
		} else if ("deleteRessource".equals(method)) {
			Service.deleteRessource(req, resp);
		} else if ("infoRessource".equals(method)) {
			Service.infoRessource(req, resp);
		} else if ("modifyRessource".equals(method)) {
			Service.modifyRessource(req, resp);
		}
		else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "Sorry..we're developing it");
			Gopage.mainPage(req, resp);
		}
	}

	public static void createSearch(HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException {
		String destination = req.getParameter("destination").toLowerCase();
		String checkin = req.getParameter("checkin");
		String checkout = req.getParameter("checkout");
		String numPeople = req.getParameter("nb");
		String type = req.getParameter("type");
		String smoker = req.getParameter("smoker");
		String info = "Result";
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("city", destination); 
		attrs.put("persons", numPeople);
		attrs.put("type", type);
		attrs.put("smoker", smoker);
		List<RessourceBean> result = RessourceDao.getRessourcesFrom(attrs);
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.setAttribute("result", result);
		Gopage.mainPage(req, resp);
	}

	public static void getRessource(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean)req.getSession().getAttribute("user")).getId();
		List<RessourceBean> ressources = RessourceDao.getRessourcesFromOwner(owner);
		req.setAttribute("ressources", ressources);
		Gopage.ressourceList(req, resp);
	}

	public static void createRessource(HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		String type = req.getParameter("type");
		float price = Float.parseFloat(req.getParameter("price"));
		int number = Integer.parseInt(req.getParameter("number"));
		String street = req.getParameter("street").toLowerCase();
		int postal = Integer.parseInt(req.getParameter("postal"));
		String city = req.getParameter("city").toLowerCase();
		int persons;
		if("room".equals(type)) persons = Integer.parseInt(req.getParameter("persons_room"));
		else persons = Integer.parseInt(req.getParameter("persons_house"));
		String smoker = req.getParameter("smoker");
		int owner = ((UserBean)session.getAttribute("user")).getId();
		RessourceBean ress = new RessourceBean();
		ress.setType(type);
		ress.setPrice(price);
		ress.setNumber(number);
		ress.setStreet(street);
		ress.setPostal(postal);
		ress.setCity(city);
		ress.setPersons(persons);
		ress.setSmoker(smoker);
		String info = "Added a " + type;
		RessourceDao.saveRessource(owner, ress);
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		Service.getRessource(req, resp);
	}

	public static void deleteRessource(HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("id");
		RessourceDao.deleteRessource(idr);
		getRessource(req, resp);
	}

	public static void infoRessource(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		String info;
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", idr);
		List<RessourceBean> ress = RessourceDao.getRessourcesFrom(attrs);
		if(ress.isEmpty()){
			info = "Ressource " + idr + "not exist";
			req.setAttribute("info", info); req.setAttribute("type", "danger");
			getRessource(req, resp);
		}
		req.setAttribute("ressource", ress.get(0));
		Gopage.infoRessource(req, resp);
	}

	public static void modifyRessource(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int id = Integer.parseInt(req.getParameter("id"));
		String type = req.getParameter("type");
		float price = Float.parseFloat(req.getParameter("price"));
		int number = Integer.parseInt(req.getParameter("number"));
		String street = req.getParameter("street").toLowerCase();
		int postal = Integer.parseInt(req.getParameter("postal"));
		String city = req.getParameter("city").toLowerCase();
		int persons;
		if("room".equals(type)) persons = Integer.parseInt(req.getParameter("persons_room"));
		else persons = Integer.parseInt(req.getParameter("persons_house"));
		String smoker = req.getParameter("smoker");
		RessourceBean ress = new RessourceBean();
		ress.setId(id);
		ress.setType(type);
		ress.setPrice(price);
		ress.setNumber(number);
		ress.setStreet(street);
		ress.setPostal(postal);
		ress.setCity(city);
		ress.setPersons(persons);
		ress.setSmoker(smoker);
		RessourceDao.modifyRessource(ress);
		req.setAttribute("ressource", ress);
		Gopage.infoRessource(req, resp);
	}
}
