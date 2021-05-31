package model;

public enum ModeleVehicule {
    BENZ("Benz", MarqueVehicule.MERCEDES),
    MEGANE("Megane", MarqueVehicule.RENAULT);

    private String designation;
    private MarqueVehicule marqueVehicule;

    private ModeleVehicule(String designation, MarqueVehicule marqueVehicule){
        this.designation = designation;
        this.marqueVehicule = marqueVehicule;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public MarqueVehicule getMarque() {
        return marqueVehicule;
    }

    public static ModeleVehicule getByName(String name){
        for(ModeleVehicule modele: values()){
            if(modele.getDesignation().equals(name)) return modele;
        }
        return null;
    }
}
