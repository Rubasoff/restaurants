package ru.karachurin.restaurants.service;

import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Денис on 15.11.2016.
 */
public interface RestaurantService {
    Restaurant get(int id) throws NotFoundException;

    Restaurant getWithVotesOnDate(int id, LocalDate date) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    Restaurant update(Restaurant restaurant) throws NotFoundException;

    Restaurant save(Restaurant restaurant);

    Collection<Restaurant> getAll();

    Collection<Restaurant> getAllWithVotesOnDate();

}
