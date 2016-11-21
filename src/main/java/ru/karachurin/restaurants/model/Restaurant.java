package ru.karachurin.restaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */
@SqlResultSetMapping(
        name = "RestaurantMapping",
        classes = @ConstructorResult(
                targetClass = Restaurant.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "contacts", type = String.class),
                        @ColumnResult(name = "votesCount", type = Integer.class)
                        }))
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    @Column (name = "address")
    private String address;

    @Column (name = "contacts")
    private String contacts;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "restaurant")
    List<Dish> menu;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    List<Vote> votes;

    @Transient
    private Integer votesCount;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, String contacts) {
        super(id, name);
        this.address = address;
        this.contacts = contacts;
    }

    public Restaurant(Integer id, String name, String address, String contacts, Integer votesCount) {
        super(id, name);
        this.address = address;
        this.contacts = contacts;
        this.votesCount = votesCount;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                "address='" + address + '\'' +
                ", contacts='" + contacts + '\'' +
                '}';
    }
}
