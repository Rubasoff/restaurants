package ru.karachurin.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.repository.RestaurantRepository;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Денис on 17.11.2016.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant getWithVotesOnDate(int id, LocalDate date) throws NotFoundException {
        return restaurantRepository.findOneWithVotesOnDate(id,date);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        restaurantRepository.delete(id);
    }

    @Override
    public Restaurant update(Restaurant restaurant) throws NotFoundException {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return (Collection<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public Collection<Restaurant> getAllWithVotesOnDate() {
        return null;
    }
}
