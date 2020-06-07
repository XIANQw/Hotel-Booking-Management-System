package jar.dao;

import jar.util.Parameter;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import jar.bean.DemandBean;

public class DemandDao {

    public static void saveDemand(DemandBean demand) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    Parameter.username, Parameter.pwd);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "insert into Demand values(0," + demand.getIdr() + "," + demand.getIdu() + ",'"
                    + demand.getCheckin().toString() + "','" + demand.getCheckout().toString() + "','"
                    + df.format(demand.getCreateTime()) + "','" + demand.getStatus() + "');";
            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if (res != 1) {
                throw new RuntimeException("error save demand....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateDemand(DemandBean demand) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    Parameter.username, Parameter.pwd);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "update Demand set idr=" + demand.getIdr() + ",idu=" + demand.getIdu() + ",checkin='"
                    + demand.getCheckin().toString() + "',checkout='" + demand.getCheckout().toString()
                    + "',create_time='" + df.format(demand.getCreateTime()) + "',status='" + demand.getStatus()
                    + "' where id=" + demand.getId();

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

    public static List<DemandBean> getDemandsFrom(HashMap<String, String> attrs) {
        ArrayList<DemandBean> demands = new ArrayList<DemandBean>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    Parameter.username, Parameter.pwd);
            String sql = "select * from Demand where ";
            for (String attr : attrs.keySet()) {
                sql += attr + "=" + "'" + attrs.get(attr) + "' and ";
            }
            sql += "1";
            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (res.next()) {
                DemandBean cmd = new DemandBean();
                cmd.setId(res.getInt(1));
                cmd.setIdr(res.getInt(2));
                cmd.setIdu(res.getInt(3));
                cmd.setCheckin(res.getDate(4));
                cmd.setCheckout(res.getDate(5));
                cmd.setCreateTime(df.parse(res.getString(6)));
                cmd.setStatus(res.getString(7));
                demands.add(cmd);
            }
            return demands;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demands;
    }

    public static void deleteDemandsFrom(HashMap<String, String> attrs) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pc3r?serverTimezone=UTC&useSSL=false",
                    Parameter.username, Parameter.pwd);
            String sql = "delete from Demand where ";
            for (String attr : attrs.keySet()) {
                sql += attr + "=" + "'" + attrs.get(attr) + "' and ";
            }
            sql += "1";
            System.out.println("sql=" + sql);
            Statement stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if (res != 1) {
                throw new RuntimeException("error save demand....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
