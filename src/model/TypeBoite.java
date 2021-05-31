package model;

public enum TypeBoite {
    MANUELLE("Manuelle"),
    AUTOMATIQUE("Automatique");

    private String designation;

    private TypeBoite(String designation){
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public static TypeBoite getByName(String name){
        for(TypeBoite boite: values()){
            if(boite.getDesignation().equals(name)) return boite;
        }
        return null;
    }

}
