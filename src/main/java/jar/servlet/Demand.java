package jar.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import jar.bean.*;
import jar.dao.*;

import java.util.*;

public class Demand {

	public static void createSearch(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		int idu = ((UserBean) req.getSession().getAttribute("user")).getId();
		String destination = req.getParameter("destination").toLowerCase();
		Date checkin = Date.valueOf(req.getParameter("checkin"));
		Date checkout = Date.valueOf(req.getParameter("checkout"));
		String numPeople = req.getParameter("nb");
		String type = req.getParameter("type");
		String smoker = req.getParameter("smoker");
		String info = "Result of command: " + checkin + " " + checkout;

		DemandBean cmd = new DemandBean();
		cmd.setCheckin(checkin);
		cmd.setCheckout(checkout);
		cmd.setIdu(idu);
		cmd.setStatus("Pending");
		req.getSession().setAttribute("cmd", cmd);

		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("city", destination);
		attrs.put("persons", numPeople);
		attrs.put("type", type);
		attrs.put("smoker", smoker);

		List<RessourceBean> tmp = RessourceDao.getRessourcesFrom(attrs), result = new ArrayList<RessourceBean>();
		for (RessourceBean res : tmp) {
			if (res.getIdu() != idu)
				result.add(res);
		}
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		req.getSession().setAttribute("result", result);
		Gopage.mainPage(req, resp);
	}

	public static void createSearchAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			resp.getWriter().write("Session invalid, reconnect please ...");
		}
		int idu = ((UserBean) req.getSession().getAttribute("user")).getId();
		String destination = req.getParameter("destination").toLowerCase();

		Date checkin = Date.valueOf(req.getParameter("checkin"));
		Date checkout = Date.valueOf(req.getParameter("checkout"));
		String numPeople = req.getParameter("nb");
		String type = req.getParameter("type");
		String smoker = req.getParameter("smoker");

		DemandBean cmd = new DemandBean();
		cmd.setCheckin(checkin);
		cmd.setCheckout(checkout);
		cmd.setIdu(idu);
		cmd.setStatus("Pending");
		req.getSession().setAttribute("cmd", cmd);

		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("city", destination);
		attrs.put("persons", numPeople);
		attrs.put("type", type);
		attrs.put("smoker", smoker);

		List<RessourceBean> tmp = RessourceDao.getRessourcesFrom(attrs);
		String resJson = "[";
		if (tmp.size() > 0) {
			for (int i = 0; i < tmp.size() - 1; i++) {
				RessourceBean res = tmp.get(i);
				if (res.getIdu() != idu)
					resJson += res.toJson();
				resJson += ",";
			}
			resJson += tmp.get(tmp.size() - 1).toJson();
		}
		resJson += "]";
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(resJson);
		System.out.println(resJson);
	}

	public static void sendDemand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		int idr = Integer.parseInt(req.getParameter("id"));
		java.util.Date createTime = new java.util.Date();
		DemandBean cmd = (DemandBean) req.getSession().getAttribute("cmd");
		cmd.setIdr(idr);
		cmd.setCreateTime(createTime);
		DemandDao.saveDemand(cmd);
		String info = "Your demand sended successfully";
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		Gopage.mainPage(req, resp);
	}

	public static void sendDemandAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			resp.getWriter().write("Session invalid, reconnect please ...");
		}
		int idr = Integer.parseInt(req.getParameter("id"));
		java.util.Date createTime = new java.util.Date();
		DemandBean cmd = (DemandBean) req.getSession().getAttribute("cmd");
		cmd.setIdr(idr);
		cmd.setCreateTime(createTime);
		DemandDao.saveDemand(cmd);
		String json = "{\"info\":\"Your demand sended successfully\"}";
		resp.getWriter().write(json);
	}

	public static void getSendedDemands(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<DemandBean> demands = DemandDao.getDemandsFrom(attrs);
		req.setAttribute("cmds", demands);
		Gopage.sendedDemandList(req, resp);
	}

	public static void getSendedDemandsAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			resp.getWriter().write("Session invalid, reconnect please ...");
		}
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<DemandBean> demands = DemandDao.getDemandsFrom(attrs);
		String json = "[";
		for (int i = 0; i < demands.size() - 1; i++) {
			attrs.clear();
			attrs.put("id", Integer.toString(demands.get(i).getIdr()));
			RessourceBean res = RessourceDao.getRessourcesFrom(attrs).get(0);
			json += "{\"demand\":" + demands.get(i).toJson() + ", \"res\":" + res.toJson() + "}, ";
		}
		if (demands.size() > 0) {
			attrs.clear();
			attrs.put("id", Integer.toString(demands.get(demands.size() - 1).getIdr()));
			RessourceBean res = RessourceDao.getRessourcesFrom(attrs).get(0);
			json += "{\"demand\":" + demands.get(demands.size() - 1).toJson() + ", \"res\":" + res.toJson() + "}";
		}
		json += "]";
		resp.getWriter().write(json);
		System.out.println(json);
	}

	public static void deleteSendedDemands(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idr", idr);
		DemandDao.deleteDemandsFrom(attrs);
		Demand.getSendedDemands(req, resp);
	}

	public static void deleteDemandAjax(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		if (!Client.sessionValide(req, resp)) {
			resp.getWriter().write("Session invalid, reconnect please ...");
		}
		String id = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", id);
		DemandDao.deleteDemandsFrom(attrs);
		resp.getWriter().write("{\"info\": \"Your demande has been deleted successfully\"}");
	}

	public static void getDemandsFromRessource(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idr", idr);
		List<DemandBean> cmds = DemandDao.getDemandsFrom(attrs);
		req.setAttribute("cmds", cmds);
		Gopage.recievedDemandList(req, resp);
	}

	public static void getRecievedDemands(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<RessourceBean> ressources = RessourceDao.getRessourcesFrom(attrs);
		List<DemandBean> demands = new ArrayList<DemandBean>();
		for (RessourceBean res : ressources) {
			attrs.clear();
			attrs.put("idr", Integer.toString(res.getId()));
			demands.addAll(DemandDao.getDemandsFrom(attrs));
		}
		req.setAttribute("cmds", demands);
		Gopage.recievedDemandList(req, resp);
	}

	public static void deleteRecievedDemands(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		String id = req.getParameter("idc");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", id);
		DemandDao.deleteDemandsFrom(attrs);
		Demand.getRecievedDemands(req, resp);
	}

	public static void acceptDemands(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("idr"), idc = req.getParameter("idc");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", idc);
		DemandBean cmd = DemandDao.getDemandsFrom(attrs).get(0);
		attrs.clear();
		attrs.put("id", idr);
		RessourceBean res = RessourceDao.getRessourcesFrom(attrs).get(0);
		attrs.clear();
		attrs.put("idr", idr);
		String status = "Accepted";
		attrs.put("status", status);
		List<DemandBean> cmdsOfRes = DemandDao.getDemandsFrom(attrs);
		boolean conflict = false;
		for (DemandBean com : cmdsOfRes) {
			Date cmdIn = java.sql.Date.valueOf(cmd.getCheckin().toString()),
					cmdOut = java.sql.Date.valueOf(cmd.getCheckout().toString()),
					comIn = java.sql.Date.valueOf(com.getCheckin().toString()),
					comOut = java.sql.Date.valueOf(com.getCheckout().toString());

			if (cmdOut.before(comIn) || cmdIn.after(comOut))
				continue;
			else {
				conflict = true;
				break;
			}
		}
		if (conflict) {
			String info = "This demand could not be accepted, it will cause a conflict";
			req.setAttribute("info", info);
			req.setAttribute("type", "danger");
			getRecievedDemands(req, resp);
		} else {
			cmd.setStatus(status);
			DemandDao.updateDemand(cmd);
			String info = "The demand has been accepted";
			req.setAttribute("info", info);
			req.setAttribute("type", "success");
			getRecievedDemands(req, resp);
		}
	}
}