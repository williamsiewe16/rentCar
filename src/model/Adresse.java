package model;

import javafx.beans.property.StringProperty;

import java.beans.Transient;

public class Adresse {
	public int getId_adresse() {
		return id_adresse;
	}

	public void setId_adresse(int id_adresse) {
		this.id_adresse = id_adresse;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public int id_adresse;
	public String rue;
	public String ville;
	public int codePostal;

	@Override
	public String toString() {
		return rue+" "+codePostal+" "+ville;
	}

	public Adresse(int id_adresse, String rue, String ville, int codePostal) {
		this.id_adresse = id_adresse;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public Adresse(){

	}
}
