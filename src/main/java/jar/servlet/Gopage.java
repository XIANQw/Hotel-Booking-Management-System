package jar.servlet;

import javax.servlet.http.*;

import jar.bean.RessourceBean;
import jar.bean.UserBean;
import jar.dao.ProfileDao;
import jar.dao.RessourceDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.*;

public class Gopage extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String page = req.getParameter("page");
        if ("accueil".equals(page)) {
            Gopage.accueil(req, resp);
        } else if ("mainPage".equals(page)) {
            Gopage.mainPage(req, resp);
        } else if ("profile".equals(page)) {
            Gopage.profile(req, resp);
        } else if ("modifyAccount".equals(page)) {
            Gopage.modifyAccount(req, resp);
        } else if ("modifyRessource".equals(page)) {
            Gopage.modifyRessource(req, resp);
        } else if ("sendedCommandeList".equals(page)) {
            Gopage.sendedCommandeList(req, resp);
        } else if ("recievedCommandeList".equals(page)) {
            Gopage.recievedCommandeList(req, resp);
        } else if ("ressourceList".equals(page)) {
            Gopage.ressourceList(req, resp);
        } else if ("infoRessource".equals(page)) {
            Gopage.infoRessource(req, resp);
        } else {
            return;
        }
    }

    public static void accueil(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
    }

    public static void mainPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
        }
    }

    public static void profile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            req.getRequestDispatcher("/static/view/profile.jsp").forward(req, resp);
        }
    }

    public static void modifyAccount(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            UserBean user = (UserBean) req.getSession().getAttribute("user");
            req.setAttribute("profile", ProfileDao.getProfileFromUser(user.getId()));
            req.getRequestDispatcher("/static/view/modifyAccount.jsp").forward(req, resp);
        }
    }

    public static void modifyRessource(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            HashMap<String, String> attrs = new HashMap<>();
            attrs.put("id", req.getParameter("id"));
            List<RessourceBean> ress = RessourceDao.getRessourcesFrom(attrs);
            req.setAttribute("ressource", ress.get(0));
            req.getRequestDispatcher("/static/view/modifyRessource.jsp").forward(req, resp);
        }
    }

    public static void sendedCommandeList(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            req.getRequestDispatcher("/static/view/sendedCommandeList.jsp").forward(req, resp);
        }
    }

    public static void recievedCommandeList(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            req.getRequestDispatcher("/static/view/recievedCommandeList.jsp").forward(req, resp);
        }
    }

    public static void ressourceList(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            req.getRequestDispatcher("/static/view/ressourceList.jsp").forward(req, resp);
        }
    }

    public static void infoRessource(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!Client.sessionValide(req, resp)) {
            accueil(req, resp);
        } else {
            req.getRequestDispatcher("/static/view/infoRessource.jsp").forward(req, resp);
        }
    }

}
