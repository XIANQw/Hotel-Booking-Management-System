package jar.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import jar.dao.*;
import jar.bean.*;

public class Ressource {
    
    public static void createRessource(HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean)req.getSession().getAttribute("user")).getId();
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
		ress.setIdu(owner);
		ress.setType(type);
		ress.setPrice(price);
		ress.setNumber(number);
		ress.setStreet(street);
		ress.setPostal(postal);
		ress.setCity(city);
		ress.setPersons(persons);
		ress.setSmoker(smoker);
		String info = "Added a " + type;
		RessourceDao.saveRessource(ress);
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		Ressource.getRessource(req, resp);
	}

	public static void deleteRessource(HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("id");
		RessourceDao.deleteRessource(idr);
		Ressource.getRessource(req, resp);
	}

    public static void getRessource(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean)req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<RessourceBean> ressources = RessourceDao.getRessourcesFrom(attrs);
		req.setAttribute("ressources", ressources);
		Gopage.ressourceList(req, resp);
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
			Ressource.getRessource(req, resp);
		}
		req.setAttribute("ressource", ress.get(0));
		Gopage.infoRessource(req, resp);
	}

	public static void getResDetailsAjax(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		resp.setCharacterEncoding("utf-8");
		if(!Client.sessionValide(req, resp)){;
			resp.getWriter().write("Session invalid, reconnect please ...");
			return ;
		}
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", idr);
		List<RessourceBean> ress = RessourceDao.getRessourcesFrom(attrs);
		ProfileBean owner = ProfileDao.getProfileFromUser(ress.get(0).getIdu());
		String data = "[" + owner.toJson() + ", " + ress.get(0).toJson() + "]";
		resp.getWriter().write(data);
		System.out.println(data);
	}


	public static void modifyRessource(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int id = Integer.parseInt(req.getParameter("id"));
		int owner = ((UserBean)req.getSession().getAttribute("user")).getId();
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
		ress.setIdu(owner);
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