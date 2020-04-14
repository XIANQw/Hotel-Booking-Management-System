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
		if ("createSearch".equals(method)){
			Commande.createSearch(req, resp);
		} else if ("getRessources".equals(method)) {
			Ressource.getRessource(req, resp);
		} else if ("createRessource".equals(method)) {
			Ressource.createRessource(req, resp);
		} else if ("deleteRessource".equals(method)) {
			Ressource.deleteRessource(req, resp);
		} else if ("infoRessource".equals(method)) {
			Ressource.infoRessource(req, resp);
		} else if ("modifyRessource".equals(method)) {
			Ressource.modifyRessource(req, resp);
		} else if ("sendCommande".equals(method)) {
			Commande.sendCommande(req, resp);
		} else if ("getSendedCommandes".equals(method)){
			Commande.getSendedCommandes(req, resp);
		} else if ("deleteSendedCommandes".equals(method)){
			Commande.deleteSendedCommandes(req, resp);
		} else if ("getCommandesFromRessource".equals(method)) {
			Commande.getCommandesFromRessource(req, resp);
		} else if ("getRecievedCommandes".equals(method)) {
			Commande.getRecievedCommandes(req, resp);
		} else if ("deleteRecievedCommandes".equals(method)) {
			Commande.deleteRecievedCommandes(req, resp);
		}
		else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "Sorry..we're developing it");
			Gopage.mainPage(req, resp);
		}
	}


}
