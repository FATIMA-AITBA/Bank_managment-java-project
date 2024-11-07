import java.util.ArrayList;
import java.util.List;

public class Client {
    private int numClient;
    private String nom;
    private String prenom;
    private String adresse;
    private String phone;
    private String email;
    private List<Compte> comptes; // Liste des comptes associés au client

    public Client(int numClient, String nom, String prenom, String adresse, String phone, String email) {
        this.numClient = numClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.phone = phone;
        this.email = email;
        this.comptes = new ArrayList<>();
    }

    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

    public List<Compte> getComptes() {
        return comptes;
    }
    // Getters et Setters
    public int getNumClient() { return numClient; }
    public void setNumClient(int numClient) { this.numClient = numClient; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String toJSON() {
        return "{" +
                "\"numClient\":" + numClient + "," +
                "\"nom\":\"" + nom + "\"," +
                "\"prenom\":\"" + prenom + "\"," +
                "\"adresse\":\"" + adresse + "\"," +
                "\"phone\":\"" + phone + "\"," +
                "\"email\":\"" + email + "\"" +
                "}";
    }
}