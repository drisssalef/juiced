package com.juicer.juiced.classes;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "juice")
public class Juice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int juice_id;
    private double juice_price;
    private String description_fruit;
    private Boolean visible_juice;

    @Lob   //large object helpt Jpa met veel data
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private byte[] speaker_photo;

    @ManyToMany
    @JoinTable(
            name = "fruit_juice",
            joinColumns = @JoinColumn(name ="juiced_id"),
            inverseJoinColumns = @JoinColumn(name="fruit_id")
    )

    private List<Fruit> fruits;


    @ManyToMany
    @JoinTable(
            name = "juice_orders",
            joinColumns = @JoinColumn(name ="juice_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id")
    )
    private List<Orders> orders;


    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Juice() {
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
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

    public double getJuice_price() {
        return juice_price;
    }

    public void setJuice_price(double juice_price) {
        this.juice_price = juice_price;
    }

    public String getDescription_fruit() {
        return description_fruit;
    }

    public void setDescription_fruit(String description_fruit) {
        this.description_fruit = description_fruit;
    }

    public Boolean getVisible_juice() {
        return visible_juice;
    }

    public void setVisible_juice(Boolean visible_juice) {
        this.visible_juice = visible_juice;
    }
}
