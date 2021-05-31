package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;


public class Client extends Personne {

	public static List<Client> clients = new ArrayList<Client>();
	public static boolean all=true;
	public static boolean init=true;
	public static int comboIndex=0;

	private int id_client;
	private Button modifier;
	private Button supprimer;
	
	public Client(int id_client, String nom, String prenom, int tel, Adresse adresse) {
		super(nom, prenom, tel,adresse);
		this.id_client = id_client;
	}

	public Client(String nom, String prenom, int tel, Adresse adresse) {
		super(nom, prenom, tel,adresse);
	}

	public Client(int id_client){
		this.id_client = id_client;
	}

	public Client(){

	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getAdresseString() { return adresse.toString(); }

	public void setModifier(Button modifier){
		this.modifier = modifier;
	}

	public void setSupprimer(Button supprimer){
		this.supprimer = supprimer;
	}

	public HBox getButtons(){
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.getChildren().addAll(modifier,supprimer);
		return hBox;
	}


}
