package jar.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jar.bean.RessourceBean;

public class RessourceDao {

    public static void saveRessource(int owner, RessourceBean ress){
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
                ress.getPersons() + "','"+
                ress.getSmoker() + "');";
            Statement stmt = con.createStatement();
            System.out.println("sql= " + sql);
            int res = stmt.executeUpdate(sql);
            if(res != 1){
                throw new RuntimeException("error insert ressource ...");
            }
            sql = "select max(id) from Ressource";
            ResultSet maxid = stmt.executeQuery(sql);
            String ressId = null;
            while(maxid.next()){
                ressId = maxid.getString(1);
            }
            sql = "insert into User_ressource values(0, '" +
            owner + "','" + ressId + "');";
            System.out.println("sql="+sql);
            res = stmt.executeUpdate(sql);
            if(res !=1){
                throw new RuntimeException("error insert User_ressource ...");
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "select * from Ressource where ";
            for(String attr: attrs.keySet()){
                if("persons".equals(attr))
                    sql += "persons > " + attrs.get(attr) + " and ";
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
                ress.setType(res.getString(2));
                ress.setPrice(res.getFloat(3));
                ress.setNumber(res.getInt(4));
                ress.setStreet(res.getString(5));
                ress.setPostal(res.getInt(6));
                ress.setCity(res.getString(7));
                ress.setPersons(res.getInt(8));
                ress.setSmoker(res.getString(9));
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

    public static List<RessourceBean> getRessourcesFromOwner(int owner){
        ArrayList<RessourceBean> ressources = new ArrayList<RessourceBean>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "select * from User_ressource where idu='" + owner + "';";
            System.out.println("sql="+sql);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                String idr = res.getString(3);
                HashMap<String, String> attrs = new HashMap<String, String>();
                attrs.put("id", idr);
                ressources.addAll(getRessourcesFrom(attrs));
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "delete from Ressource where id='" + idr + "';";
            System.out.println("sql="+sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if(res != 1){
                throw new RuntimeException("error delete ressource ...");
            }
            sql = "delete from User_ressource where idr='" + idr + "';";
            res = stmt.executeUpdate(sql);
            System.out.println("sql="+sql);
            if(res != 1){
                throw new RuntimeException("error delete user_ressource ...");
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
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
