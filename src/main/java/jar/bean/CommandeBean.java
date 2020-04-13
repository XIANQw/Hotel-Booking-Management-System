package jar.bean;

import java.sql.Date;

import javax.servlet.http.HttpServlet;

public class CommandeBean extends HttpServlet {
    private int id;
    private int sender;
    private int ressource;
    private Date checkin;
    private Date checkout;

    public CommandeBean(){}

    public void setId(int id){
        this.id = id;
    }
    public void setSender(int sender){
        this.sender = sender;
    }
    public void setRessource(int ressource){
        this.ressource = ressource;
    }
    public void setCheckin(Date checkin){
        this.checkin = checkin;
    }
    public void setCheckout(Date checkout){
        this.checkout = checkout;
    }

    public int getId(){
        return this.id;
    }
    public int getSender(){
        return this.sender;
    }
    public int getRessource(){
        return this.ressource;
    }
    public Date getCheckin(){
        return this.checkin;
    }
    public Date getCheckout(){
        return this.checkout;
    }

}