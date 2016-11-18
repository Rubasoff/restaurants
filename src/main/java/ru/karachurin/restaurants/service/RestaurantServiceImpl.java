package ru.karachurin.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.model.Vote;
import ru.karachurin.restaurants.repository.RestaurantRepository;
import ru.karachurin.restaurants.repository.VoteRepository;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Денис on 17.11.2016.
 */
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant getWithVotesOnDate(int id, LocalDate date) throws NotFoundException {
        Restaurant restaurant = restaurantRepository.findOne(id);
        restaurant.setVotes(voteRepository.getAllByRestaurantIdAndDate(id, date));
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
    public Collection<Restaurant> getAll() {
        return (Collection<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public Collection<Restaurant> getAllWithVotesOnDate(LocalDate date) {
        Collection<Restaurant> restaurants = restaurantRepository.getAllWithVotesOnDate(date);
        //If there are null, it means that all restaurants don't have a votes on that date.
        //Lets get all restaurants and make their votes collection is empty to avoid Lazy error
        if (restaurants == null) return getAllRestaurantsWithEmptyVotesCollection();
        else return restaurants;
    }

    private Collection<Restaurant> getAllRestaurantsWithEmptyVotesCollection(){
        List<Vote> votes = new ArrayList<>();
        Collection<Restaurant>  restaurants = (Collection<Restaurant>) restaurantRepository.findAll();
            for (Restaurant restaurant : restaurants) {
                restaurant.setVotes(votes);
            }
            return restaurants;
    }


}
