package models;

public class Guest {

    private int id;
    private String name;
    private String familyName;
    private String telephone;

    public Guest(int id, String name, String familyName, String telephone) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
