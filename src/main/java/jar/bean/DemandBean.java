package jar.bean;

import java.sql.Date;

import javax.servlet.http.HttpServlet;

public class DemandBean extends HttpServlet {
    private int id;
    private int idr;
    private int idu;
    private Date checkin;
    private Date checkout;
    private java.util.Date createTime;
    private String status;

    public DemandBean() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public int getIdr() {
        return this.idr;
    }

    public int getIdu() {
        return this.idu;
    }

    public Date getCheckin() {
        return this.checkin;
    }

    public Date getCheckout() {
        return this.checkout;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public String getStatus() {
        return this.status;
    }

    public String toJson() {
        String str = "{\"id\":" + id + ",\"idr\":" + idr + ",\"idu\":" + idu + ",\"checkin\":\"" + checkin.toString()
                + "\",\"checkout\":\"" + checkout.toString() + "\",\"createTime\":\"" + createTime.toString()
                + "\",\"status\":\"" + status + "\"}";
        return str;
    }

    @Override
    public String toString() {
        String str = "cmd" + id + ",idr=" + idr + ",idu=" + idu + ",in=" + checkin.toString() + "out="
                + checkout.toString() + "creatTime=" + createTime.toString() + "," + status;
        return str;
    }
}