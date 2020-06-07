package jar.dao;
import jar.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jar.bean.RessourceBean;

public class RessourceDao {

    public static void saveRessource(RessourceBean ress){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", Parameter.username, Parameter.pwd);
            String sql = "insert into Ressource values(0,'" +
                ress.getIdu() + "','" +
                ress.getType() + "','" +
                ress.getPrice() + "','"+
                ress.getNumber() + "','"+
                ress.getStreet() + "','"+
                ress.getPostal() + "','"+
                ress.getCity() + "','"+
                ress.getPersons() + "','"+
                ress.getSmoker() + "');";
            Statement stmt = con.createStatement();
            System.out.println("sql= " + sql);
            int res = stmt.executeUpdate(sql);
            if(res != 1){
                throw new RuntimeException("error insert ressource ...");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<RessourceBean> getRessourcesFrom(HashMap<String, String> attrs){
        ArrayList<RessourceBean> ressources = new ArrayList<RessourceBean>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", Parameter.username, Parameter.pwd);
            String sql = "select * from Ressource where ";
            for(String attr: attrs.keySet()){
                if("persons".equals(attr))
                    sql += "persons >= " + attrs.get(attr) + " and ";
                else
                    sql +=  attr + "=" + "'" + attrs.get(attr) + "' and ";
            }
            sql += "1";
            System.out.println("sql="+sql);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                RessourceBean ress = new RessourceBean();
                ress.setId(res.getInt(1));
                ress.setIdu(res.getInt(2));
                ress.setType(res.getString(3));
                ress.setPrice(res.getFloat(4));
                ress.setNumber(res.getInt(5));
                ress.setStreet(res.getString(6));
                ress.setPostal(res.getInt(7));
                ress.setCity(res.getString(8));
                ress.setPersons(res.getInt(9));
                ress.setSmoker(res.getString(10));
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

    public static void deleteRessource(String idr){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", Parameter.username, Parameter.pwd);
            String sql = "delete from Ressource where id='" + idr + "';";
            System.out.println("sql="+sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if(res != 1){
                throw new RuntimeException("error delete ressource ...");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void modifyRessource(RessourceBean res) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", Parameter.username, Parameter.pwd);
            String sql = "update Ressource set type='"+ res.getType() + "',price=" + res.getPrice()
            + ",number=" + res.getNumber() + ",street='" + res.getStreet() + "', postal='" + res.getPostal()
            + "', city='" + res.getCity() + "', persons=" + res.getPersons() + ", smoker='" + res.getSmoker() + "' where id=" + res.getId() + ";";
            System.out.println("sql="+sql);
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(sql);
            if(result != 1){
                throw new RuntimeException("error modify ressource ...");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
