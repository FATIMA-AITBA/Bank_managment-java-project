import java.util.Date;

public class Compte {
    private int numCompte;
    private Date dateCreation;
    private Date dateUpdate;
    private String devise;

    public Client client; // Référence au client propriétaire
    public Banque banque; // Référence à la banque associée

    public Compte(int numCompte, Date dateCreation, Date dateUpdate, String devise, Client client, Banque banque) {
        this.numCompte = numCompte;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.devise = devise;
        this.client = client;
        this.banque = banque;
    }


    // Getters et Setters
    public int getNumCompte() { return numCompte; }
    public void setNumCompte(int numCompte) { this.numCompte = numCompte; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

    public Date getDateUpdate() { return dateUpdate; }
    public void setDateUpdate(Date dateUpdate) { this.dateUpdate = dateUpdate; }

    public String getDevise() { return devise; }
    public void setDevise(String devise) { this.devise = devise; }

    public String toJSON() {
        return "{" +
                "\"numCompte\":" + numCompte + "," +
                "\"dateCreation\":\"" + dateCreation + "\"," +
                "\"dateUpdate\":\"" + dateUpdate + "\"," +
                "\"devise\":\"" + devise + "\"" +
                "}";
    }
}