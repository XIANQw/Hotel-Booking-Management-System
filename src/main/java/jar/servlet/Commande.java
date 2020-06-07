package jar.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import jar.bean.*;
import jar.dao.*;

import java.util.*;

public class Commande {

	public static void createSearch(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
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

		CommandeBean cmd = new CommandeBean();
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

	public static void sendCommande(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		int idr = Integer.parseInt(req.getParameter("id"));
		java.util.Date createTime = new java.util.Date();
		CommandeBean cmd = (CommandeBean) req.getSession().getAttribute("cmd");
		cmd.setIdr(idr);
		cmd.setCreateTime(createTime);
		CommandeDao.saveCommande(cmd);
		String info = "Your commande sended successfully";
		req.setAttribute("info", info);
		req.setAttribute("type", "success");
		Gopage.mainPage(req, resp);
	}

	public static void getSendedCommandes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<CommandeBean> commandes = CommandeDao.getCommandesFrom(attrs);
		req.setAttribute("cmds", commandes);
		Gopage.sendedCommandeList(req, resp);
	}

	public static void deleteSendedCommandes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idr", idr);
		CommandeDao.deleteCommandesFrom(attrs);
		Commande.getSendedCommandes(req, resp);
	}

	public static void getCommandesFromRessource(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("id");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idr", idr);
		List<CommandeBean> cmds = CommandeDao.getCommandesFrom(attrs);
		req.setAttribute("cmds", cmds);
		Gopage.recievedCommandeList(req, resp);
	}

	public static void getRecievedCommandes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		int owner = ((UserBean) req.getSession().getAttribute("user")).getId();
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("idu", Integer.toString(owner));
		List<RessourceBean> ressources = RessourceDao.getRessourcesFrom(attrs);
		List<CommandeBean> commandes = new ArrayList<CommandeBean>();
		for (RessourceBean res : ressources) {
			attrs.clear();
			attrs.put("idr", Integer.toString(res.getId()));
			commandes.addAll(CommandeDao.getCommandesFrom(attrs));
		}
		req.setAttribute("cmds", commandes);
		Gopage.recievedCommandeList(req, resp);
	}

	public static void deleteRecievedCommandes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		String id = req.getParameter("idc");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", id);
		CommandeDao.deleteCommandesFrom(attrs);
		Commande.getRecievedCommandes(req, resp);
	}

	public static void acceptCommandes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!Client.sessionValide(req, resp)) {
			;
			Gopage.accueil(req, resp);
		}
		String idr = req.getParameter("idr"), idc = req.getParameter("idc");
		HashMap<String, String> attrs = new HashMap<>();
		attrs.put("id", idc);
		CommandeBean cmd = CommandeDao.getCommandesFrom(attrs).get(0);
		attrs.clear();
		attrs.put("id", idr);
		RessourceBean res = RessourceDao.getRessourcesFrom(attrs).get(0);
		attrs.clear();
		attrs.put("idr", idr);
		String status = "Accepted";
		attrs.put("status", status);
		List<CommandeBean> cmdsOfRes = CommandeDao.getCommandesFrom(attrs);
		boolean conflict = false;
		for (CommandeBean com : cmdsOfRes) {
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
			String info = "This commande could not be accepted, it will cause a conflict";
			req.setAttribute("info", info);
			req.setAttribute("type", "danger");
			getRecievedCommandes(req, resp);
		} else {
			cmd.setStatus(status);
			CommandeDao.updateCommande(cmd);
			String info = "The commande has been accepted";
			req.setAttribute("info", info);
			req.setAttribute("type", "success");
			getRecievedCommandes(req, resp);
		}
	}
}