package com.juicer.juiced.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "juice")
public class Juice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int juice_id;
    private double price_juice;
    private String description_fruit;

    @Column(name = "visible_juice")
    private Boolean visibleJuice;

    private byte[] photo_juice;

    @ManyToMany
    @JoinTable(
            name = "fruit_juice",
            joinColumns = @JoinColumn(name ="juice_id"),
            inverseJoinColumns = @JoinColumn(name="fruit_id")
    )
    private List<Fruit> fruits;



    @ManyToMany(mappedBy = "juices")
    @JsonIgnore
    private List<Orders> orders;


    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Juice() {
    }

    public byte[] getPhoto_juice() {
        return photo_juice;
    }

    public void setPhoto_juice(byte[] photo_juice) {
        this.photo_juice = photo_juice;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public int getJuice_id() {
        return juice_id;
    }

    public void setJuice_id(int juice_id) {
        this.juice_id = juice_id;
    }

    public double getPrice_juice() {
        return price_juice;
    }

    public void setPrice_juice(double price_juice) {
        this.price_juice = price_juice;
    }

    public String getDescription_fruit() {
        return description_fruit;
    }

    public void setDescription_fruit(String description_fruit) {
        this.description_fruit = description_fruit;
    }

    public Boolean getVisible_juice() {
        return visibleJuice;
    }

    public void setVisible_juice(Boolean visible_juice) {
        this.visibleJuice = visible_juice;
    }
}
