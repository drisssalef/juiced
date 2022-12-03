package com.juicer.juiced.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orders_id;

    private int orders_nummer;
    private String orders_product_desc;
    private Boolean orders_is_paid;

    @ManyToMany
    @JoinTable(
            name = "juice_orders",
            joinColumns = @JoinColumn(name ="orders_id"),
            inverseJoinColumns = @JoinColumn(name = "juice_id")
    )
    private List<Juice> juices;

    public Orders() {
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getOrders_nummer() {
        return orders_nummer;
    }

    public void setOrders_nummer(int orders_nummer) {
        this.orders_nummer = orders_nummer;
    }

    public String getOrders_product_desc() {
        return orders_product_desc;
    }

    public void setOrders_product_desc(String orders_product_desc) {
        this.orders_product_desc = orders_product_desc;
    }

    public Boolean getOrders_is_paid() {
        return orders_is_paid;
    }

    public void setOrders_is_paid(Boolean orders_is_paid) {
        this.orders_is_paid = orders_is_paid;
    }

    public List<Juice> getJuices() {
        return juices;
    }

    public void setJuices(List<Juice> juices) {
        this.juices = juices;
    }
}
