package jar.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import jar.bean.*;
import jar.dao.CommandeDao;
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
		} else if ("sendCommande".equals(method)) {
			Service.sendCommande(req, resp);
		} else if ("getCommandes".equals(method)){
			Service.getCommandes(req, resp);
		}
		else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "Sorry..we're developing it");
			Gopage.mainPage(req, resp);
		}
	}

	public static void createSearch(HttpServletRequest req,
	HttpServletResponse resp) throws ServletException, IOException {
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int idu = ((UserBean)req.getSession().getAttribute("user")).getId();
		String destination = req.getParameter("destination").toLowerCase();
		Date checkin = Date.valueOf(req.getParameter("checkin"));
		Date checkout = Date.valueOf(req.getParameter("checkout"));
		String numPeople = req.getParameter("nb");
		String type = req.getParameter("type");
		String smoker = req.getParameter("smoker");
		String info = "Result of command: " + checkin + " " + checkout;
		
		CommandeBean cmd = new CommandeBean();
		cmd.setCheckin(checkin); 
		cmd.setCheckout(checkout); 
		cmd.setIdu(idu);
		req.getSession().setAttribute("cmd", cmd);

		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("city", destination); 
		attrs.put("persons", numPeople);
		attrs.put("type", type);
		attrs.put("smoker", smoker);
		
		List<RessourceBean> tmp = RessourceDao.getRessourcesFrom(attrs), result = new ArrayList<RessourceBean>();
		for(RessourceBean res : tmp){
			if(res.getIdu() != idu) result.add(res);
		}
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.getSession().setAttribute("result", result);
		Gopage.mainPage(req, resp);
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

	public static void sendCommande(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int idr = Integer.parseInt(req.getParameter("id"));
		java.util.Date createTime = new java.util.Date();
		CommandeBean cmd = (CommandeBean)req.getSession().getAttribute("cmd");
		cmd.setIdr(idr);
		cmd.setCreateTime(createTime);
		CommandeDao.saveCommande(cmd);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String info = "user=" + cmd.getIdu() + 
		" ressource=" + cmd.getIdr() + 
		" checkin=" + cmd.getCheckin().toString() + 
		" checkout="+ cmd.getCheckout().toString() + 
		" createTime=" + df.format(cmd.getCreateTime());
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		Gopage.mainPage(req, resp);
	}

	public static void getCommandes(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean)req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<CommandeBean> commandes = CommandeDao.getCommandesFrom(attrs);
		req.setAttribute("cmds", commandes);
		Gopage.commandeList(req, resp);
	}
}
