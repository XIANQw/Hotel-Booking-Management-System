package jar.bean;

import java.sql.Date;

import javax.servlet.http.HttpServlet;

public class CommandeBean extends HttpServlet {
    private int id;
    private int idr;
    private int idu;
    private Date checkin;
    private Date checkout;
    private java.util.Date createTime;
    

    public CommandeBean(){}

    public void setId(int id){
        this.id = id;
    }
    public void setIdr(int idr){
        this.idr = idr;
    }
    public void setIdu(int idu){
        this.idu = idu;
    }
    public void setCheckin(Date checkin){
        this.checkin = checkin;
    }
    public void setCheckout(Date checkout){
        this.checkout = checkout;
    }
    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    public int getId(){
        return this.id;
    }
    public int getIdr(){
        return this.idr;
    }
    public int getIdu(){
        return this.idu;
    }
    public Date getCheckin(){
        return this.checkin;
    }
    public Date getCheckout(){
        return this.checkout;
    }
    public java.util.Date getCreateTime(){
        return this.createTime;
    }

}