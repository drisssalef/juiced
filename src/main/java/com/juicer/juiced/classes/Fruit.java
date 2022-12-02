package com.juicer.juiced.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity(name="fruit")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fruit_id;
    private String fruit_name;
    private double price_fruit;
    private String description_fruit;
    private byte[] photo_fruit;


    @ManyToMany(mappedBy = "fruits")
    @JsonIgnore
    private List<Juice> juices;


    public Fruit() {
    }

    public byte[] getPhoto_fruit() {
        return photo_fruit;
    }

    public void setPhoto_fruit(byte[] photo_fruit) {
        this.photo_fruit = photo_fruit;
    }

    public int getFruit_id() {
        return fruit_id;
    }

    public List<Juice> getJuices() {
        return juices;
    }

    public void setJuices(List<Juice> juices) {
        this.juices = juices;
    }

    public void setFruit_id(int fruit_id) {
        this.fruit_id = fruit_id;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public double getPrice_fruit() {
        return price_fruit;
    }

    public void setPrice_fruit(double price_fruit) {
        this.price_fruit = price_fruit;
    }

    public String getDescription_fruit() {
        return description_fruit;
    }

    public void setDescription_fruit(String description_fruit) {
        this.description_fruit = description_fruit;
    }
}
