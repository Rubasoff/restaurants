package ru.karachurin.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.repository.RestaurantRepository;
import ru.karachurin.restaurants.repository.VoteRepository;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Денис on 17.11.2016.
 */
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    //TODO Exceptions
    //TODO refactor to List

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;

    @Override
    public Restaurant get(int id) throws NotFoundException {
        Restaurant restaurant = restaurantRepository.findOne(id);
        restaurant.setVotesCount(restaurant.getVotes().size());
        return restaurant;
    }

    @Override
    public Restaurant getWithVotesOnDate(int id, LocalDate date) throws NotFoundException {
        Restaurant restaurant = restaurantRepository.findOne(id);
        restaurant.setVotesCount(voteRepository.getAllByRestaurantIdAndDate(id, date).size());
        return restaurant;
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
    public List<Restaurant> getAll() {
        List<Restaurant> allRestaurants = (List<Restaurant>) restaurantRepository.findAll();
        for (Restaurant restaurant : allRestaurants) {
            restaurant.setVotesCount(restaurant.getVotes().size());
        }
        return allRestaurants;
    }

    @Override
    public List<Restaurant> getAllWithVotesOnDate(LocalDate date) {
        //Due to the fact that some restaurants may be without any votes, they don't get in query result
        // , so we need get all restaurants first, then get restaurants that have a votes on current date
        // , and set votesCount to each restaurant
        List<Restaurant> restaurantsWithVotes = restaurantRepository.getAllWithVotesOnDate(date);
        List<Restaurant> allRestaurants = (List<Restaurant>) restaurantRepository.findAll();

        for (Restaurant restaurant : allRestaurants) {
            if (restaurantsWithVotes.contains(restaurant)){
                int idx = restaurantsWithVotes.indexOf(restaurant);
                restaurant.setVotesCount(restaurantsWithVotes.get(idx).getVotes().size());
            }
            else {
                restaurant.setVotesCount(0);
            }
        }
        return allRestaurants;
    }
}

