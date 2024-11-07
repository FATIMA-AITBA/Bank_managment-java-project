import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Création des objets Client et Banque
        Client client1 = new Client(1, "Dupont", "Jean", "123 Rue Principale", "0123456789", "jean.dupont@example.com");
        Banque banque1 = new Banque(101, "France");

        // Format de la date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Conversion des chaînes de caractères en objets Date
        Date dateCreation1 = sdf.parse("2024-01-01");
        Date dateCreation2 = sdf.parse("2024-02-01");
        Date dateDerniereModification1 = sdf.parse("2024-10-01");
        Date dateDerniereModification2 = sdf.parse("2024-11-01");

        // Création des comptes avec les dates converties en objets Date
        Compte compte1 = new Compte(1001, dateCreation1, dateDerniereModification1, "EUR", client1, banque1);
        Compte compte2 = new Compte(1002, dateCreation2, dateDerniereModification2, "USD", client1, banque1);

        // Ajouter les comptes au client et à la banque
        client1.ajouterCompte(compte1);
        client1.ajouterCompte(compte2);
        banque1.ajouterCompte(compte1);
        banque1.ajouterCompte(compte2);

        // Afficher les comptes du client
        System.out.println("Comptes du client " + client1.getNom() + " :");
        for (Compte compte : client1.getComptes()) {
            System.out.println("Numéro de compte: " + compte.getNumCompte() + ", Devise: " + compte.getDevise());
        }
    }
}
