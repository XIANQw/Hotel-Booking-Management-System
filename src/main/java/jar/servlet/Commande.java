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
    
    public static void getCommandesFromRessource(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		if(!Client.sessionValide(req, resp)){;
			Gopage.accueil(req, resp);
        }
        String idr = req.getParameter("id");
        HashMap<String, String> attrs = new HashMap<>();
        attrs.put("idr", idr);
        List<CommandeBean> cmds = CommandeDao.getCommandesFrom(attrs);
        req.setAttribute("cmds", cmds);
        Gopage.commandeList(req, resp);
    }
}