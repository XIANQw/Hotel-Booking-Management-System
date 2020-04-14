package jar.dao;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import jar.bean.CommandeBean;

public class CommandeDao {

    public static void saveCommande(CommandeBean commande){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "insert into Commande values(0," +
            commande.getIdu() + "," +
            commande.getIdr() + ",'"+
            commande.getCheckin().toString() + "','" +
            commande.getCheckout().toString() +  "','" +
            df.format(commande.getCreateTime()) + "');";
            Statement stmt = con.createStatement();
            System.out.println("sql= " + sql);
            int res = stmt.executeUpdate(sql);
            if(res != 1){
                throw new RuntimeException("error save commande....");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<CommandeBean> getCommandesFrom(HashMap<String, String> attrs){
        ArrayList<CommandeBean> commandes = new ArrayList<CommandeBean>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "select * from Commande where "; 
            for(String attr : attrs.keySet()){
                sql +=  attr + "=" + "'" + attrs.get(attr) + "' and ";
            }
            sql += "1";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                CommandeBean cmd = new CommandeBean();
                cmd.setId(res.getInt(1));
                cmd.setIdu(res.getInt(2));
                cmd.setIdr(res.getInt(3));
                cmd.setCheckin(res.getDate(4));
                cmd.setCheckout(res.getDate(5));
                commandes.add(cmd);
            }
            return commandes;
        } catch (SQLException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return commandes;
    }
}
