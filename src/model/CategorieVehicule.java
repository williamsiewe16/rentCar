package model;

public enum CategorieVehicule {
    ECONOMIQUE("Economique",100),
    CONFORT("Confort",150),
    LUXE("Luxe",200);

    private String designation;
    private int tarifJournalier;

    private CategorieVehicule(String designation, int tarifJournalier){
        this.designation = designation;
        this.tarifJournalier = tarifJournalier;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getTarifJournalier() {
        return tarifJournalier;
    }

    public void setTarifJournalier(int tarifJournalier) {
        this.tarifJournalier = tarifJournalier;
    }

    public static CategorieVehicule getByName(String name){
        for(CategorieVehicule cat: values()){
            if(cat.getDesignation().equals(name)) return cat;
        }
        return null;
    }
}
