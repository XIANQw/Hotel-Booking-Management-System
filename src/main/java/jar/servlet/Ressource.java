package jar.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import jar.dao.*;
import jar.util.ToJson;
import jar.bean.*;

public class Ressource {

	public static void createRessourceAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			String json = ToJson.toJson("", "Session invalid, reconnect please ...", 0);
			resp.getWriter().write(json);
			return ;
		}
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		String type = req.getParameter("type").trim();
		float price = Float.parseFloat(req.getParameter("price").trim());
		int number = Integer.parseInt(req.getParameter("number").trim());
		String street = req.getParameter("street").trim().toLowerCase();
		int postal = Integer.parseInt(req.getParameter("postal").trim());
		String city = req.getParameter("city").trim().toLowerCase();
		int persons;
		if ("room".equals(type))
			persons = Integer.parseInt(req.getParameter("persons_room"));
		else
			persons = Integer.parseInt(req.getParameter("persons_house"));
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
		String json = ToJson.toJson("", info, 1);
		resp.getWriter().write(json);
		System.out.println(json);
	}

	public static void deleteResAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			String json = ToJson.toJson("", "Session invalid, reconnect please ...", 0);
			resp.getWriter().write(json);
			return;
		}
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idr", idr);
		List<DemandBean> dmds = DemandDao.getDemandsFrom(attrs);
		if(dmds.size() > 0) {
			String info = "There are some demands of this resouce, you can not delete this resource";
			resp.getWriter().write(ToJson.toJson("", info, -1));
		} else {
			RessourceDao.deleteRessource(idr);
			String info = "This resource has been deleted successfully";
			resp.getWriter().write(ToJson.toJson("", info, 1));
		}
	}

	public static void getResListAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			String json = ToJson.toJson("", "Session invalid, reconnect please ...", 0);
			resp.getWriter().write(json);
			return;
		}
		int owner = Integer.parseInt(req.getParameter("id"));
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<RessourceBean> ressources = RessourceDao.getRessourcesFrom(attrs);
		String json = "[";
		for (int i = 0; i < ressources.size() - 1; i++) {
			json += ressources.get(i).toJson() + ", ";
		}
		if (ressources.size() > 0) {
			json += ressources.get(ressources.size() - 1).toJson();
		}
		json += "]";
		resp.getWriter().write(json);
		System.out.println(json);
	}

	public static void getResDetailsAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			String json = ToJson.toJson("", "Session invalid, reconnect please ...", 0);
			resp.getWriter().write(json);
			return;
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
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		int id = Integer.parseInt(req.getParameter("id"));
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		String type = req.getParameter("type");
		float price = Float.parseFloat(req.getParameter("price"));
		int number = Integer.parseInt(req.getParameter("number"));
		String street = req.getParameter("street").toLowerCase();
		int postal = Integer.parseInt(req.getParameter("postal"));
		String city = req.getParameter("city").toLowerCase();
		int persons;
		if ("room".equals(type))
			persons = Integer.parseInt(req.getParameter("persons_room"));
		else
			persons = Integer.parseInt(req.getParameter("persons_house"));
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