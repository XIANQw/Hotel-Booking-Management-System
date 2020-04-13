package jar.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jar.bean.ProfileBean;

public class ProfileDao {

public static void saveProfile(int idu, ProfileBean profile){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver"); //sql配置
        Connection con =
         DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
        Statement stmt = con.createStatement();
        String sql;
        ProfileBean oldProfile = getProfileFromUser(idu);
        if (oldProfile ==  null){
            //如果没有profile就先insert fail没关系
            sql = "insert into Profile values("+ idu +",'"+ profile.getNom() +"','" + profile.getPrenom()
            +"','"+ profile.getEmail() +"','" +profile.getAdresse() +"'," + profile.getTelephone() + ");";
        }else{
            //有就update
            sql = "update Profile set nom ='"+ profile.getNom() +"',prenom ='"+ profile.getPrenom()
            +"',email ='"+profile.getEmail()+"',adresse='"
            + profile.getAdresse() +"',telephone ="+ profile.getTelephone() +" where id = "+ idu;
        }
        int res = stmt.executeUpdate(sql);
        System.out.println("sql="+sql);
        res = stmt.executeUpdate(sql);
        if(res !=1){
            throw new RuntimeException("error update Profile ...");
        }
    }
     catch (SQLException e){
        e.printStackTrace();
    } catch(Exception e){
        e.printStackTrace();
    }
}

    public static ProfileBean getProfileFromUser(int idu){
        ProfileBean profile = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "select * from Profile where id='" + idu + "';";
            System.out.println("sql="+sql);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()){
                profile = new ProfileBean();
                profile.setId(res.getInt(1));
                profile.setNom(res.getString(2));
                profile.setPrenom(res.getString(3));
                profile.setEmail(res.getString(4));
                profile.setAdresse(res.getString(5));
                profile.setTelephone(res.getInt(6));
            }
            return profile;
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return profile;
    }
}
