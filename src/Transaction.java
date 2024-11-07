import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    public enum TypeTransaction {VIRIN, VIREST, VIRMULTA, VIRCHAC}
    private  TypeTransaction type;
    private String reference;
    private Date time;
    private List<Compte> comptesDestinataires; // Liste de comptes pour plusieurs destinataires

    public Transaction(TypeTransaction type, Date time) {
        this.type = type;
        this.reference = UUID.randomUUID().toString();
        this.time = time;
        this.comptesDestinataires = new ArrayList<>();
    }


    public void ajouterCompteDestinataire(Compte compte) {
        comptesDestinataires.add(compte);
    }

    public TypeTransaction getType() { return type; }
    public void setType(TypeTransaction type) { this.type = type; }

    public String getReference() { return reference; }

    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; }

    public void calculerTypeTransaction(Compte compteSource, List<Compte> comptesDest) {
        boolean memeBanque=false;
        if (comptesDest.size() > 1) {
            // VIRMULTA : transfert vers plusieurs comptes
            this.type = TypeTransaction.VIRMULTA;
        } else {
            // Récupérer le compte destinataire unique s'il existe
            Compte compteDest = comptesDest.get(0);

            // Vérifier si les banques et pays sont identiques ou différents
            if(compteSource.banque.getIdBanque()==(compteDest.banque.getIdBanque())){
                memeBanque=true;
            }
            boolean memePays = compteSource.client.getAdresse().equals(compteDest.client.getAdresse());

            if (memeBanque) {
                // VIRIN : transaction entre deux comptes de la même banque
                this.type = TypeTransaction.VIRIN;
            } else if (memePays) {
                // VIREST : transaction entre deux comptes du même pays mais de banques différentes
                this.type = TypeTransaction.VIREST;
            } else {
                // VIRCHAC : transaction entre deux comptes de banques et de pays différents
                this.type = TypeTransaction.VIRCHAC;
            }
        }
    }


    public String toJSON() {
        return "{" +
                "\"type\":\"" + type + "\"," +
                "\"reference\":\"" + reference + "\"," +
                "\"time\":\"" + time + "\"" +
                "}";
    }
}