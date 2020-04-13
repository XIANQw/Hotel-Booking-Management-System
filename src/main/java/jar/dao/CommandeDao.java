package jar.dao;

import java.sql.Statement;
import java.util.*;
import java.sql.*;

import jar.bean.CommandeBean;

public class CommandeDao {

    public static void saveCommande(CommandeBean commande){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "insert into Commande values(0,'" +
            commande.getSender() + "','" +
            commande.getRessource() + "','"+
            commande.getCheckin() + "','" +
            commande.getCheckout() +  "');";
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

    public static List<CommandeBean> getCommandesFrom(String attribut, int value){
        ArrayList<CommandeBean> commandes = new ArrayList<CommandeBean>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false", "xian", "");
            String sql = "select * from Commande where " + attribut + "=" + "'" + value +"';";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                CommandeBean cmd = new CommandeBean();
                cmd.setId(res.getInt(1));
                cmd.setSender(res.getInt(2));
                cmd.setRessource(res.getInt(3));
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
