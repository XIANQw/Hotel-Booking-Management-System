package jar.servlet;

import javax.servlet.http.*;

import java.io.IOException;

import javax.servlet.*;

public class Gopage extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        String page = req.getParameter("page");
        if("accueil".equals(page)){
            accueil(req, resp);
        }
        else if ("mainPage".equals(page)) {
            mainPage(req, resp);
        }
        else if ("profile".equals(page)) {
            profile(req, resp);
        }
        else if("modifyAccount".equals(page)){
        modifyAccount(req,resp);
        }
        else if("modifyRes".equals(page)){
        modifyRes(req,resp);
        }
        else if("commandeList".equals(page)){
        commandeList(req,resp);
        }
        else if("ressource".equals(page)){
        ressource(req,resp);
        }
        else {
                    return ;
            }
        }

    public void accueil(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
    }

    public void mainPage(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        if(!Client.sessionValide(req, resp)){
            accueil(req, resp);
        } else{
            req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
        }
    }

    public void profile(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        if(!Client.sessionValide(req, resp)){
            accueil(req, resp);
        } else{
            req.getRequestDispatcher("/static/view/profile.jsp").forward(req, resp);
        }
    }

    public void modifyAccount(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        if(!Client.sessionValide(req, resp)){
            accueil(req, resp);
        } else{
            req.getRequestDispatcher("/static/view/modifyAccount.jsp").forward(req, resp);
        }
    }
    public void modifyRes(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        if(!Client.sessionValide(req, resp)){
            accueil(req, resp);
        } else{
            req.getRequestDispatcher("/static/view/modifyRes.jsp").forward(req, resp);
        }
    }
    public void commandeList(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        if(!Client.sessionValide(req, resp)){
            accueil(req, resp);
        } else{
            req.getRequestDispatcher("/static/view/commandeList.jsp").forward(req, resp);
        }
    }
    public void ressource(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        if(!Client.sessionValide(req, resp)){
            accueil(req, resp);
        } else{
            req.getRequestDispatcher("/static/view/ressource.jsp").forward(req, resp);
        }
    }

}
