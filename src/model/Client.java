package model;

public class Client extends Personne {

	private int id_client;
	
	public Client(int id_client, String nom, String prenom, int tel, Adresse adresse) {
		super(nom, prenom, tel,adresse);
		this.id_client = id_client;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getAdresseString() { return adresse.toString(); }

}
