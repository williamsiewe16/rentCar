package model;

import java.util.ArrayList;
import java.util.List;

public class Vehicule {
    public static List<Vehicule> vehicules = new ArrayList<Vehicule>();
    public static boolean all=true;
    public static boolean init=true;
    public static int comboCategoryIndex=0;
    public static int comboMarqueIndex=0;
    public static boolean locationBool;


    private String immatriculation;
    private int kilometrage;
    private boolean climatisation;
    private Agence currentAgency;
    private TypeBoite typeBoite;
    private ModeleVehicule modele;
    private CategorieVehicule categorie;
    private TypeCarburant typeCarburant;


    public Vehicule(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Vehicule(String immatriculation, int kilometrage, boolean climatisation, Agence currentAgency, TypeBoite typeBoite, ModeleVehicule modele, CategorieVehicule categorie, TypeCarburant typeCarburant) {
        this.immatriculation = immatriculation;
        this.kilometrage = kilometrage;
        this.climatisation = climatisation;
        this.currentAgency = currentAgency;
        this.typeBoite = typeBoite;
        this.modele = modele;
        this.categorie = categorie;
        this.typeCarburant = typeCarburant;
    }

    public Vehicule(String immatriculation, int kilometrage, boolean climatisation, TypeBoite typeBoite, ModeleVehicule modele, CategorieVehicule categorie, TypeCarburant typeCarburant) {
        this.immatriculation = immatriculation;
        this.kilometrage = kilometrage;
        this.climatisation = climatisation;
        this.typeBoite = typeBoite;
        this.modele = modele;
        this.categorie = categorie;
        this.typeCarburant = typeCarburant;
    }


    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public boolean isClimatisation() {
        return climatisation;
    }

    public void setClimatisation(boolean climatisation) {
        this.climatisation = climatisation;
    }

    public Agence getCurrentAgency() {
        return currentAgency;
    }

    public void setCurrentAgency(Agence currentAgency) {
        this.currentAgency = currentAgency;
    }

    public TypeBoite getTypeBoite() {
        return typeBoite;
    }

    public void setTypeBoite(TypeBoite typeBoite) {
        this.typeBoite = typeBoite;
    }

    public ModeleVehicule getModele() {
        return modele;
    }

    public void setModele(ModeleVehicule modele) {
        this.modele = modele;
    }

    public CategorieVehicule getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieVehicule categorie) {
        this.categorie = categorie;
    }

    public TypeCarburant getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(TypeCarburant typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public String getAgenceString(){
        return currentAgency.getNom();
    }

    public String getClimatisationString(){
        return (climatisation ? "Climatisé" : "Non climatisé");
    }

    public String getModeleString(){
        return modele.getMarque().getDesignation()+" "+modele.getDesignation();
    }

    public String getCategorieString(){
        return categorie.getDesignation();
    }

    public String getTypeCarburantString(){
        return typeCarburant.getDesignation();
    }

    public String getTypeBoiteString(){
        return typeBoite.getDesignation();
    }



}
