package app.model;

import org.bson.types.ObjectId;

public class President {
    private ObjectId id;
    private String name;
    private int age;
    private String party;

    public President(String name, int age, String party) {
        this.id = new ObjectId();
        this.name = name;
        this.age = age;
        this.party = party;
    }

    public String getName() {
        return name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
