package jar.dao;

import java.sql.*;
import java.util.HashMap;

import jar.bean.UserBean;

public class UserDao {

    public static void saveUser(UserBean user){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "root", "");
            String sql = "insert into User values(0,'" + user.getUsername() + "','"+ user.getPassword() + "');";
            Statement stmt = con.createStatement();
            System.out.println("sql= " + sql);
            int res = stmt.executeUpdate(sql);
            if(res != 1){
                throw new RuntimeException("error signup....");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, UserBean> getUsers(){
        HashMap<String, UserBean> users = new HashMap<String, UserBean>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "root", "");
            String sql = "select * from User;";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                String username = res.getString(2);
                String password = res.getString(3);
                UserBean tmp = new UserBean();
                tmp.setId(res.getInt(1));
                tmp.setUsername(username);
                tmp.setPassword(password);
                users.put(username, tmp);
            }
            return users;
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static String getUsername(int id) {
        String username = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "root", "");
            String sql = "select * from User where id=" + id + ";";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                username = res.getString("username");
            }
            return username;
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return username;
    }
}
