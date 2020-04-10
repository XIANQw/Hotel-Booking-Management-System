package jar.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import jar.bean.UserBean;
import jar.dao.UserDao;

public class Signup extends HttpServlet{

    static Connection con;
    static Statement stmt;
    int res;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
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
}