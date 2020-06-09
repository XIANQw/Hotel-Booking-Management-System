package jar.servlet;

import javax.servlet.http.*;

import jar.bean.*;
import jar.dao.*;
import jar.util.ToJson;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.*;

public class Client extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String method = req.getParameter("method");
        if ("Login".equals(method)) {
            Client.login(req, resp);
        } else if ("Signup".equals(method)) {
            Client.signup(req, resp);
        } else if ("Logout".equals(method)) {
            Client.logout(req, resp);
        } else if ("modifyProfileAjax".equals(method)) {
            Client.modifyProfileAjax(req, resp);
        } else if ("getProfileAjax".equals(method)) {
            Client.getProfileAjax(req, resp);
        } else {
            req.setAttribute("type", "danger");
            req.setAttribute("info", "undefine " + method);
            Gopage.accueil(req, resp);
        }
    }

    public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        HashMap<String, UserBean> users = UserDao.getUsers();
        String info, type;
        if (users == null || !users.containsKey(username)) {
            info = "That's a wrong username";
            type = "danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            Gopage.accueil(req, resp);
            return;
        }
        UserBean user = users.get(username);
        if (!password.equals(user.getPassword())) {
            info = "Your password is wrong";
            type = "danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            Gopage.accueil(req, resp);
            return;
        }
        info = "Welcome [" + username + "] !!!";
        type = "success";
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        req.setAttribute("users", users);
        req.setAttribute("type", type);
        req.setAttribute("info", info);
        Gopage.mainPage(req, resp);
    }

    public static void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String info, type;
        if (username == null || username.equals("")) {
            info = "Username is invalid";
            type = "danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            Gopage.accueil(req, resp);
            return;
        }
        HashMap<String, UserBean> users = UserDao.getUsers();
        if (users.containsKey(username)) {
            info = "Username is used by other users";
            type = "danger";
            req.setAttribute("info", info);
            req.setAttribute("type", type);
            Gopage.accueil(req, resp);
            return;
        }
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        UserDao.saveUser(user);
        Client.login(req, resp);
    }

    public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Client.sessionValide(req, resp)) {
            req.getSession().invalidate();
        }
        Gopage.accueil(req, resp);
        return;
    }

    public static boolean sessionValide(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            req.setAttribute("info", "Session invalid, reconnect please ...");
            req.setAttribute("type", "warning");
            return false;
        }
        return true;
    }

    public static void getProfileAjax(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        if (!Client.sessionValide(req, resp)) {
            String json = ToJson.toJson("", "Session invalid, reconnect please ...", 0);
            resp.getWriter().write(json);
            return;
        }
        int idu = Integer.parseInt(req.getParameter("id"));
        ProfileBean profile = ProfileDao.getProfileFromUser(idu);
        String json = "";
        if (profile == null) {
            String info = "Your account has not yet a profile";
            json = ToJson.toJson("", info, -1);
        } else {
            json = ToJson.toJson(profile.toJson(), "", 1);
        }
        resp.getWriter().write(json);
        System.out.println(json);
    }

    public static void modifyProfileAjax(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        if (!Client.sessionValide(req, resp)) {
            String json = ToJson.toJson("", "Session invalid, reconnect please ...", 0);
            resp.getWriter().write(json);
            return;
        }
        String nom = req.getParameter("nom").trim();
        String prenom = req.getParameter("prenom").trim();
        String email = req.getParameter("email").trim();
        String adresse = req.getParameter("adresse").trim();
        String telephone = req.getParameter("tel").trim();
        int id = ((UserBean) req.getSession().getAttribute("user")).getId();

        ProfileBean profile = new ProfileBean();
        profile.setId(id);
        profile.setNom(nom);
        profile.setPrenom(prenom);
        profile.setEmail(email);
        profile.setAdresse(adresse);
        profile.setTelephone(telephone);
        ProfileDao.saveProfile(id, profile);
        String json = ToJson.toJson("", "Your profile has been created successfully", 1);
        resp.getWriter().write(json);
    }

}