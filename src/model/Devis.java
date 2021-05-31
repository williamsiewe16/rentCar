package model;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Random;

public class Devis {
    private String numero;
    private Date dateDebut;
    private Date dateFin;
    private boolean assurance;

    public Devis(String numero, Date dateDebut, Date dateFin, boolean assurance) {
        this.numero = numero;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.assurance = assurance;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date datedebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean getAssurance() {
        return assurance;
    }

    public void setAssurance(boolean assurance) {
        this.assurance = assurance;
    }

    public static String generateString() {
        String str="";
        Random rand = new Random();
        for(int i = 0 ; i < 7 ; i++){
            char c = (char)(rand.nextInt(26) + 97);
            str += c;
        }
        return str.toUpperCase();
    }
}
