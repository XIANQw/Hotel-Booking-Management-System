package jar.bean;

public class ProfileBean {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;

    public ProfileBean() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String toJson() {
        String str = "{\"id\":" + id + ",\"nom\":\"" + nom + "\",\"prenom\":\"" + prenom + "\",\"email\":\"" + email
                + "\",\"adresse\":\"" + adresse + "\",\"telephone\":\"" + telephone + "\"}";
        return str;
    }

}
