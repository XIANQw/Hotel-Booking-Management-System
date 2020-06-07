package jar.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class Service extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		System.out.println("service="+method);
		if ("createSearch".equals(method)){
			Demand.createSearch(req, resp);
		} else if ("createSearchAjax".equals(method)){
			Demand.createSearchAjax(req, resp);	
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
		} else if ("sendDemand".equals(method)) {
			Demand.sendDemand(req, resp);
		} else if ("getSendedDemands".equals(method)){
			Demand.getSendedDemands(req, resp);
		} else if ("deleteSendedDemands".equals(method)){
			Demand.deleteSendedDemands(req, resp);
		} else if ("getDemandsFromRessource".equals(method)) {
			Demand.getDemandsFromRessource(req, resp);
		} else if ("getRecievedDemands".equals(method)) {
			Demand.getRecievedDemands(req, resp);
		} else if ("deleteRecievedDemands".equals(method)) {
			Demand.deleteRecievedDemands(req, resp);
		} else if ("acceptDemand".equals(method)){
			Demand.acceptDemands(req, resp);
		}
		else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "Sorry..we're developing it");
			Gopage.mainPage(req, resp);
		}
	}

}
