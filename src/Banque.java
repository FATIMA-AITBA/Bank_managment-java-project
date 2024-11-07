import java.util.ArrayList;
import java.util.List;

public class Banque {
    private int idBanque;
    private String pays;
    private List<Compte> comptes; // Liste des comptes gérés par la banque

    public Banque(int idBanque, String pays) {
        this.idBanque = idBanque;
        this.pays = pays;
        this.comptes = new ArrayList<>();
    }

    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    // Getters et Setters
    public int getIdBanque() { return idBanque; }
    public void setIdBanque(int idBanque) { this.idBanque = idBanque; }

    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }
}
