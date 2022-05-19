package com.example.babycareco225;
import com.google.firebase.database.Exclude;
import java.util.Date;

public class Baby {
    public Baby(){}
    public Baby(String id, int age, String name, float height, float weight) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    private String name;
    private float height;
    private float weight;
    private Date dateofBirth;
}
