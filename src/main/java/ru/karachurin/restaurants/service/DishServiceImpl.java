package ru.karachurin.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.repository.DishRepository;
import ru.karachurin.restaurants.repository.RestaurantRepository;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.util.Collection;

/**
 * Created by Денис on 16.11.2016.
 */
@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Dish get(int id) throws NotFoundException {
        return dishRepository.findOne(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        dishRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        dishRepository.deleteAll();
    }

    @Override
    public Dish update(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish save(Dish dish, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        dish.setRestaurant(restaurant);
        return dishRepository.save(dish);
    }

    @Override
    public Collection<Dish> getAll() {
        return (Collection<Dish>) dishRepository.findAll();
    }

    @Override
    public Collection<Dish> getAllFromRestaurant(int restaurantId) {
        return dishRepository.getAllFromRestaurant(restaurantId);
    }
}
