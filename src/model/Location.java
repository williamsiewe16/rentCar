package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.sql.Date;

public class Location {
    private Client client;
    private Vehicule vehicule;
    private Devis devis;
    private double niveauCarburant;
    private EtatVehicule etatVehicule;
    private Date dateFinReelle=null;
    private double prixTotal;

    private Button devisButton;
    private Button factureButton;
    private Button supprimer;

    private static int penaliteNiveauCarburant=50;
    private static int montantAssurance=50;
    private static int penaliteRetard=20;


    public Location(Client client, Vehicule vehicule, Devis devis) {
        this.client = client;
        this.vehicule = vehicule;
        this.devis = devis;
    }

    public Location(Client client, Vehicule vehicule, Devis devis, Date dateFinReelle, double prixTotal) {
        this.client = client;
        this.vehicule = vehicule;
        this.devis = devis;
        this.dateFinReelle = dateFinReelle;
        this.prixTotal = prixTotal;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public double getNiveauCarburant() {
        return niveauCarburant;
    }

    public void setNiveauCarburant(double niveauCarburant) {
        this.niveauCarburant = niveauCarburant;
    }

    public EtatVehicule getEtatVehicule() {
        return etatVehicule;
    }

    public void setEtatVehicule(EtatVehicule etatVehicule) {
        this.etatVehicule = etatVehicule;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public double getDevisPrice(){
        // tarif*nbJours
        int nbJours = (int)((devis.getDateFin().getTime() - devis.getDateDebut().getTime())/(1000*60*60*24));
        if(nbJours==0) nbJours=1;
        double total = vehicule.getCategorie().getTarifJournalier()*nbJours;
        return total;
    }

    public void setPrixTotal() {
        // tarif*nbJours
        int nbJours = (int)((dateFinReelle.getTime() - devis.getDateDebut().getTime())/(1000*60*60*24));
        int nbJoursRetard = (int)((dateFinReelle.getTime() - devis.getDateFin().getTime())/(1000*60*60*24));

        if(nbJoursRetard <= 0) nbJoursRetard=0;
        if(nbJours==0) nbJours=1;
        double total = vehicule.getCategorie().getTarifJournalier()*nbJours;

        // etat vehicule
        double penaliteEtatVehicule = etatVehicule.getValue()*EtatVehicule.penalite;

        // niveau carburant
        double niveauCarburant=(1-getNiveauCarburant())*penaliteNiveauCarburant;

        // retard
        double penaliteRetardTotal = penaliteRetard*nbJoursRetard;

        // assurance
        if(devis.getAssurance()) this.prixTotal = total+montantAssurance+niveauCarburant+penaliteRetardTotal;
        else this.prixTotal = total+penaliteEtatVehicule+niveauCarburant+penaliteRetardTotal;
    }

    public void setDevisButton(Button devisButton){
        this.devisButton = devisButton;
    }

    public void setFactureButton(Button factureButton){
        this.factureButton = factureButton;
    }

    public void setSupprimer(Button supprimer){
        this.supprimer = supprimer;
    }

    public HBox getButtons(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_CENTER);
        if(dateFinReelle == null) factureButton.setText("valider le retour");
        hBox.getChildren().addAll(factureButton,devisButton);
        if(dateFinReelle == null) hBox.getChildren().add(supprimer);
        return hBox;
    }

    public String getVehiculeString(){
        return vehicule.getImmatriculation();
    }

    public String getDateDebutString(){
        return devis.getDateDebut().toString();
    }

    public String getDateFinString(){
        return (dateFinReelle == null) ? "" : dateFinReelle.toString();
    }

    public String getClientString(){
        return client.getNom()+" "+client.getPrenom();
    }

}
