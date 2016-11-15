package ru.karachurin.restaurants.service;

import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Денис on 15.11.2016.
 */
public interface DishService {
    Dish get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    Dish update(Dish dish);

    Dish save(Dish dish);

    Collection<Dish> getAllFromRestaurantOnDate(int id, int restaurantId, LocalDate date);
}
