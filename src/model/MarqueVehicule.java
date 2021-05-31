package model;

public enum MarqueVehicule {
    MERCEDES("Mercedes"),
    TOYOTA("Toyota"),
    RENAULT("Renault");

    private String designation;

    private MarqueVehicule(String designation){
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
