package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
    private IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty rules = new SimpleStringProperty(this,"rules");
    private StringProperty kategori = new SimpleStringProperty(this, "kategori");

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getRules() {
        return rules.get();
    }

    public StringProperty rulesProperty() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules.set(rules);
    }

    public String getKategori() {
        return kategori.get();
    }

    public StringProperty kategoriProperty() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori.set(kategori);
    }
}
