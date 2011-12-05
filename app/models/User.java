package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model{
    public String somefield;

    private String manualProperty;

    public String getManualProperty() {
        return manualProperty;
    }

    public void setManualProperty(String manualProperty) {
        this.manualProperty = manualProperty;
    }
}
