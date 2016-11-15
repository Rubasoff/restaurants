package ru.karachurin.restaurants.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    @Column (name = "address")
    private String address;

    @Column (name = "contacts")
    private String contacts;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    List<Dish> menu;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, String contacts, List<Dish> menu) {
        super(id, name);
        this.address = address;
        this.contacts = contacts;
        this.menu = menu;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "address='" + address + '\'' +
                ", contacts='" + contacts + '\'' +
                ", menu=" + menu +
                '}';
    }
}
