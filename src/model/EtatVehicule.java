package model;

public enum EtatVehicule {
    TRES_BON(0,"Très Bon"),
    BON(1, "Bon"),
    MOYEN(2, "Moyen"),
    MAUVAIS(3, "Mauvais"),
    TRES_MAUVAIS(4, "Très Mauvais");

    private int value;
    private String designation;
    public static int penalite = 30;

    EtatVehicule(int value, String designation){
        this.value = value;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public int getValue(){
        return value;
    }

    public static EtatVehicule getByName(String etat){
        for(EtatVehicule etatVehicule: values()){
            if(etatVehicule.getDesignation().equals(etat)) return etatVehicule;
        }
        return null;
    }
}
