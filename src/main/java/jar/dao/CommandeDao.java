package jar.dao;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import jar.bean.CommandeBean;

public class CommandeDao {

    public static void saveCommande(CommandeBean commande) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    "xian", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "insert into Commande values(0," + commande.getIdr() + "," + commande.getIdu() + ",'"
                    + commande.getCheckin().toString() + "','" + commande.getCheckout().toString() + "','"
                    + df.format(commande.getCreateTime()) + "','" + commande.getStatus() + "');";
            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if (res != 1) {
                throw new RuntimeException("error save commande....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCommande(CommandeBean commande) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    "xian", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "update Commande set idr=" + commande.getIdr() + ",idu=" + commande.getIdu() + ",checkin='"
                    + commande.getCheckin().toString() + "',checkout='" + commande.getCheckout().toString()
                    + "',create_time='" + df.format(commande.getCreateTime()) + "',status='" + commande.getStatus()
                    + "' where id=" + commande.getId();

            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            res = stmt.executeUpdate(sql);
            if (res != 1) {
                throw new RuntimeException("error update Profile ...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<CommandeBean> getCommandesFrom(HashMap<String, String> attrs) {
        ArrayList<CommandeBean> commandes = new ArrayList<CommandeBean>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    "xian", "");
            String sql = "select * from Commande where ";
            for (String attr : attrs.keySet()) {
                sql += attr + "=" + "'" + attrs.get(attr) + "' and ";
            }
            sql += "1";
            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (res.next()) {
                CommandeBean cmd = new CommandeBean();
                cmd.setId(res.getInt(1));
                cmd.setIdr(res.getInt(2));
                cmd.setIdu(res.getInt(3));
                cmd.setCheckin(res.getDate(4));
                cmd.setCheckout(res.getDate(5));
                cmd.setCreateTime(df.parse(res.getString(6)));
                cmd.setStatus(res.getString(7));
                commandes.add(cmd);
            }
            return commandes;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public static void deleteCommandesFrom(HashMap<String, String> attrs) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    "xian", "");
            String sql = "delete from Commande where ";
            for (String attr : attrs.keySet()) {
                sql += attr + "=" + "'" + attrs.get(attr) + "' and ";
            }
            sql += "1";
            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if (res != 1) {
                throw new RuntimeException("error save commande....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
