package jar.servlet;

import javax.servlet.http.*;

import jar.bean.UserBean;
import jar.dao.UserDao;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;

public class Login extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

}