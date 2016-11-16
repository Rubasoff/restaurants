package ru.karachurin.restaurants.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Денис on 15.11.2016.
 */
@Entity
@Table(name = "user_votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_votes_unique_user_id_date_idx")})
public class Vote extends NamedEntity {
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer id, String name, LocalDate date, User user, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "date=" + date +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
