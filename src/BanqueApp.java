import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import java.util.*;

public class BanqueApp extends Application {
    private Map<Integer, Client> clients = new HashMap<>();
    private Map<Integer, Compte> comptes = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Application Banque");

        // Création des sections
        TabPane tabPane = new TabPane();

        Tab ajoutClientTab = new Tab("Ajout Client");
        ajoutClientTab.setContent(createAjoutClientTab());

        Tab creationCompteTab = new Tab("Création Compte");
        creationCompteTab.setContent(createCreationCompteTab());

        Tab transactionTab = new Tab("Transactions");
        transactionTab.setContent(createTransactionTab());

        tabPane.getTabs().addAll(ajoutClientTab, creationCompteTab, transactionTab);


        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private VBox createAjoutClientTab() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");

        TextField adresseField = new TextField();
        adresseField.setPromptText("Adresse");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Téléphone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        Button ajouterClientButton = new Button("Ajouter Client");
        ajouterClientButton.setOnAction(e -> {
            int numClient = clients.size() + 1; // Générer un numéro de client unique
            Client client = new Client(numClient, nomField.getText(), prenomField.getText(),
                    adresseField.getText(), phoneField.getText(), emailField.getText());
            clients.put(numClient, client);
            System.out.println("Client ajouté : " + client.toJSON());
            nomField.clear();
            prenomField.clear();
            adresseField.clear();
            phoneField.clear();
            emailField.clear();
        });

        vbox.getChildren().addAll(nomField, prenomField, adresseField, phoneField, emailField, ajouterClientButton);

        return vbox;
    }

    private VBox createCreationCompteTab() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        TextField numCompteField = new TextField();
        numCompteField.setPromptText("Numéro Compte");

        DatePicker dateCreationPicker = new DatePicker();
        dateCreationPicker.setPromptText("Date de création");

        TextField deviseField = new TextField();
        deviseField.setPromptText("Devise");

        // Menu déroulant pour sélectionner le client
        ComboBox<Client> clientComboBox = new ComboBox<>();
        clientComboBox.getItems().addAll(clients.values()); // Ajouter tous les clients existants
        clientComboBox.setPromptText("Sélectionner un Client");

        // Menu déroulant pour sélectionner la banque
        ComboBox<Banque> banqueComboBox = new ComboBox<>();
        banqueComboBox.getItems().addAll(getBanques()); // Ajouter toutes les banques disponibles
        banqueComboBox.setPromptText("Sélectionner une Banque");

        Button creerCompteButton = new Button("Créer Compte");
        creerCompteButton.setOnAction(e -> {
            int numCompte = Integer.parseInt(numCompteField.getText());
            Date dateCreation = java.sql.Date.valueOf(dateCreationPicker.getValue());
            String devise = deviseField.getText();

            Client client = clientComboBox.getValue();
            Banque banque = banqueComboBox.getValue();

            if (client != null && banque != null) {
                // Créer le compte avec le client et la banque sélectionnés
                Compte compte = new Compte(numCompte, dateCreation, dateCreation, devise, client, banque);
                comptes.put(numCompte, compte);
                System.out.println("Compte créé : " + compte.toJSON());

                // Effacer les champs après création
                numCompteField.clear();
                deviseField.clear();
                clientComboBox.setValue(null);
                banqueComboBox.setValue(null);
            } else {
                System.out.println("Erreur : Veuillez sélectionner un client et une banque.");
            }
        });

        vbox.getChildren().addAll(numCompteField, dateCreationPicker, deviseField, clientComboBox, banqueComboBox, creerCompteButton);

        return vbox;
    }

    // Section Transactions
    private VBox createTransactionTab() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        // Menu déroulant pour sélectionner le compte source
        ComboBox<Compte> compteSourceComboBox = new ComboBox<>();
        compteSourceComboBox.getItems().addAll(comptes.values()); // Ajouter tous les comptes existants
        compteSourceComboBox.setPromptText("Sélectionner le Compte Source");

        // Menu déroulant pour sélectionner les comptes destinataires
        ComboBox<Compte> compteDestComboBox = new ComboBox<>();
        compteDestComboBox.getItems().addAll(comptes.values()); // Ajouter tous les comptes existants
        compteDestComboBox.setPromptText("Sélectionner le(s) Compte(s) Destination(s)");

        Button calculerTransactionButton = new Button("Calculer Transaction");
        calculerTransactionButton.setOnAction(e -> {
            Compte compteSource = compteSourceComboBox.getValue();
            Compte compteDest = compteDestComboBox.getValue(); // Liste des comptes destinataires

            if (compteSource != null && compteDest != null) {
                // Appel à la méthode calculerTypeTransaction avec un compte source et une liste de comptes destinataires
                List<Compte> comptesDest = new ArrayList<>();
                comptesDest.add(compteDest); // Vous pouvez ajouter plusieurs comptes destinataires ici

                Transaction transaction = new Transaction(Transaction.TypeTransaction.VIRIN, new Date());
                transaction.calculerTypeTransaction(compteSource, comptesDest); // Calculer le type de transaction
                System.out.println("Transaction calculée : " + transaction.toJSON());
            } else {
                System.out.println("Erreur : Veuillez sélectionner un compte source et un ou plusieurs comptes destinataires.");
            }
        });

        vbox.getChildren().addAll(compteSourceComboBox, compteDestComboBox, calculerTransactionButton);

        return vbox;
    }

    // Méthode pour obtenir la liste des banques
    private List<Banque> getBanques() {
        return Arrays.asList(new Banque(1, "France"), new Banque(2, "Allemagne"));
    }
}
