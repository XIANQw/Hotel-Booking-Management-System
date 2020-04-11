package jar.servlet;

import javax.servlet.http.*;

import jar.bean.UserBean;
import jar.dao.UserDao;

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
		} else {
			return ;
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
        req.getRequestDispatcher("Login").forward(req, resp);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession(false);
        if(session != null){
            session.removeAttribute("user");
        }
        resp.sendRedirect("http://localhost:8080/microproject/static/view/accueil.jsp");
        return ;
    }

}