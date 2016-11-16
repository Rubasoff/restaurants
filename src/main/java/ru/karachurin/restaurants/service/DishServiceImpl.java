package ru.karachurin.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.repository.DishRepository;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Денис on 16.11.2016.
 */
@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository repository;

    @Override
    public Dish get(int id) throws NotFoundException {
        return repository.findOne(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public Dish update(Dish dish) {
        return null;
    }

    @Override
    public Dish save(Dish dish) {
        return null;
    }

    @Override
    public Collection<Dish> getAllFromRestaurantOnDate(int id, int restaurantId, LocalDate date) {
        return null;
    }
}
