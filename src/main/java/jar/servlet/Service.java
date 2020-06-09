package jar.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class Service extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		System.out.println("service=" + method);
		if ("createSearchAjax".equals(method)) {
			Demand.createSearchAjax(req, resp);
		} else if ("getResListAjax".equals(method)) {
			Ressource.getResListAjax(req, resp);
		} else if ("createRessource".equals(method)) {
			Ressource.createRessource(req, resp);
		} else if ("deleteResAjax".equals(method)) {
			Ressource.deleteResAjax(req, resp);
		} else if ("getResDetailsAjax".equals(method)) {
			Ressource.getResDetailsAjax(req, resp);
		} else if ("modifyRessource".equals(method)) {
			Ressource.modifyRessource(req, resp);
		} else if ("sendDemandAjax".equals(method)) {
			Demand.sendDemandAjax(req, resp);
		} else if ("getSendedDemandsAjax".equals(method)) {
			Demand.getSendedDemandsAjax(req, resp);
		} else if ("deleteDemandAjax".equals(method)) {
			Demand.deleteDemandAjax(req, resp);
		} else if ("getDemandsFromRessource".equals(method)) {
			Demand.getDemandsFromRessource(req, resp);
		} else if ("getRecievedDemandsAjax".equals(method)) {
			Demand.getRecievedDemandsAjax(req, resp);
		} else if ("acceptDemandAjax".equals(method)) {
			Demand.acceptDemandAjax(req, resp);
		} else {
			req.setAttribute("type", "danger");
			req.setAttribute("info", "Sorry..we're developing it");
			Gopage.mainPage(req, resp);
		}
	}

}
