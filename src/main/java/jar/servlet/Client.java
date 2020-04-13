package jar.servlet;

import javax.servlet.http.*;

import jar.bean.*;
import jar.dao.*;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.*;

public class Client extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
		String method = req.getParameter("method");
		if ("Login".equals(method)) {
            login(req, resp);
		} else if ("Signup".equals(method)) {
			signup(req, resp);
		} else if ("Logout".equals(method)) {
			logout(req, resp);
        } else if ("modifyProfile".equals(method)) {
            modifyProfile(req, resp);
        } else if ("getProfile".equals(method)) {
            getProfile(req, resp);
        } else {
            req.setAttribute("type", "danger");
            req.setAttribute("info", "undefine " + method);
            req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
		} 
    }


    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        HashMap<String, UserBean> users = UserDao.getUsers();
        String info, type;
        if(users==null || !users.containsKey(username)){
            info = "That's a wrong username"; type="danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
            return ;
        }
        UserBean user = users.get(username);
        if(!password.equals(user.getPassword())){
            info = "Your password is wrong"; type="danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
            return ;
        }
        info="Welcome [" + username + "] !!!"; type = "success";
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        req.setAttribute("users", users);
        req.setAttribute("type", type);
        req.setAttribute("info", info);
        req.getRequestDispatcher("/static/view/mainPage.jsp").forward(req, resp);
    }

    public void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String info, type;
        if(username==null || username.equals("")){
            info = "Username is invalid"; type = "danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
            return ;
        }
        HashMap<String, UserBean> users = UserDao.getUsers();
        if(users.containsKey(username)){
            info = "Username is used by other users"; type = "danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            req.getRequestDispatcher("/static/view/accueil.jsp").forward(req, resp);
            return ;
        }
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        UserDao.saveUser(user);
        req.getRequestDispatcher("Client?method=Login").forward(req, resp);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(Client.sessionValide(req, resp)) {
            req.getSession().removeAttribute("user");
        }
        resp.sendRedirect("http://localhost:8080/microproject/static/view/accueil.jsp");
        return ;
    }

    public static boolean sessionValide(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            req.setAttribute("info", "Session invalid, reconnect please ...");
            req.setAttribute("type", "warning");
            return false;
        }
        return true;
    }

    public static void getProfile(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        if(!Client.sessionValide(req, resp)){
            req.getRequestDispatcher("Gopage?page=accueil").forward(req, resp);
        }
        String info;
        int id = ((UserBean)req.getSession().getAttribute("user")).getId();
        ProfileBean profile = ProfileDao.getProfileFromUser(id);
        if (profile == null){
            info = "You have not yet a profile, you can set your information here";
            req.setAttribute("info", info); req.setAttribute("type", "warning");
            req.getRequestDispatcher("Gopage?page=modifyAccount").forward(req, resp);
        }
        req.setAttribute("profile", profile);
        req.getRequestDispatcher("/static/view/profile.jsp").forward(req, resp);
    }

    public static void modifyProfile(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
      if(!Client.sessionValide(req, resp)){
        req.getRequestDispatcher("Gopage?page=accueil").forward(req, resp);
      }

      String nom = req.getParameter("nom");
      String prenom = req.getParameter("prenom");
      String email =req.getParameter("email");
      String adresse = req.getParameter("adresse");
      int telephone = Integer.parseInt(req.getParameter("tel"));
      int id = ((UserBean)req.getSession().getAttribute("user")).getId();
      
      ProfileBean profile = new ProfileBean();
      profile.setId(id);
      profile.setNom(nom);
      profile.setPrenom(prenom);
      profile.setEmail(email);
      profile.setAdresse(adresse);
      profile.setTelephone(telephone);
      ProfileDao.saveProfile(id, profile);
      req.setAttribute("profile", profile);
      req.getRequestDispatcher("Client?method=getProfile").forward(req, resp);
    }

}