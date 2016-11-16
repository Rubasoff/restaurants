package ru.karachurin.restaurants.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Денис on 15.11.2016.
 */
@Entity
@Table (name = "dishes")
public class Dish extends NamedEntity {
    @Column (name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, double price) {
        super(id, name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Dish setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                '}';
    }
}
