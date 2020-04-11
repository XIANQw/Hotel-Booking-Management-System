package jar.bean;

public class RessourceBean {
    private int id;
    private String type;
    private int price;
    private int number;
    private String street;
    private int postal;
    private String city;
    private String ressourceType;
    private Boolean smoker;
    private int owner;

    public RessourceBean(){}
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public int getPrice(){
        return this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getNumber(){
        return this.number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public String getStreet(){
        return this.street;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public int getPostal(){
        return this.postal;
    }
    public void setPostal(int postal){
        this.postal = postal;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public boolean getSmoker(){
        return this.smoker;
    }
    public void setSmoker(boolean smoker){
        this.smoker = smoker;
    }
    public String getRessourceType(){
        return this.ressourceType;
    }
    public void setRessourceType(String ressourceType){
        this.ressourceType = ressourceType;
    }
    public int getOwner(){
        return this.owner;
    }
    public void setOwner(int owner){
        this.owner = owner;
    }
    
}