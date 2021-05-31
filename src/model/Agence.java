package model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Agence {

    private int id_agence;
    private String nom;
    private int tel;
    private int latitude;
    private int longitude;
    private int nb_max_vehicule;
    private Adresse adresse;

    public Agence(int id_agence, String nom, int tel, int latitude, int longitude, int nb_max_vehicule, Adresse adresse) {
        this.id_agence = id_agence;
        this.nom = nom;
        this.tel = tel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nb_max_vehicule = nb_max_vehicule;
        this.adresse = adresse;
    }

    public Agence(int id_agence, String nom) {
        this.id_agence = id_agence;
        this.nom = nom;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getNb_max_vehicule() {
        return nb_max_vehicule;
    }

    public void setNb_max_vehicule(int nb_max_vehicule) {
        this.nb_max_vehicule = nb_max_vehicule;
    }

    public String getAdresseString() { return adresse.toString(); }

}
