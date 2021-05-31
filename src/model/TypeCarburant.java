package model;

public enum TypeCarburant {
    GAZOLE("Gazole"),
    ESSENCE("Essence"),
    SP95("SP95"),
    GPL("GPL"),
    ELECTRIQUE("Electrique");

    private String designation;

    private TypeCarburant(String designation){
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public static TypeCarburant getByName(String name){
        for(TypeCarburant carburant: values()){
            if(carburant.getDesignation().equals(name)) return carburant;
        }
        return null;
    }
}
