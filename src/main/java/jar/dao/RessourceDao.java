package jar.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jar.bean.RessourceBean;

public class RessourceDao {

    public static void saveUser(RessourceBean ress){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "insert into Ressource values(0,'" + 
                ress.getType() + "','" + 
                ress.getPrice() + "','"+
                ress.getNumber() + "','"+
                ress.getStreet() + "','"+
                ress.getPostal() + "','"+
                ress.getCity() + "','"+
                ress.getRessourceType() + "','"+
                ress.getSmoker() + "','" +
                ress.getOwner() + "');";
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

    public static List<RessourceBean> getRessourcesFrom(String attribut, int value){
        ArrayList<RessourceBean> ressources = new ArrayList<RessourceBean>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "select * from Ressource where " + attribut + "=" + "'" + value +"';";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                RessourceBean ress = new RessourceBean(); 
                ress.setId(res.getInt(1));
                ress.setType(res.getString(2));
                ress.setPrice(res.getInt(3));
                ress.setNumber(res.getInt(4));
                ress.setStreet(res.getString(5));
                ress.setPostal(res.getInt(6));
                ress.setCity(res.getString(7));
                ress.setRessourceType(res.getString(8));
                ress.setSmoker(res.getBoolean(9));
                ress.setOwner(res.getInt(10));
                ressources.add(ress);
            }
            return ressources;
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return ressources;
    }
}