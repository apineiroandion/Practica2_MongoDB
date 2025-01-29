package app.model;

import org.bson.types.ObjectId;

public class Country {
    private String name;
    private String organization;
    private String parties;
    private ObjectId president_id;

    public Country(String name, String organization, String parties, President president) {
        this.name = name;
        this.organization = organization;
        this.parties = parties;
        this.president_id = president.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getParties() {
        return parties;
    }

    public void setParties(String parties) {
        this.parties = parties;
    }

    public ObjectId getPresident_id() {
        return president_id;
    }

    public void setPresident_id(ObjectId president_id) {
        this.president_id = president_id;
    }
}
