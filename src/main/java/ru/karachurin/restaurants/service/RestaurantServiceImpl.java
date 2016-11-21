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
    @PersistenceContext
    private EntityManager em;

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
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getAllWithVotesOnDate(LocalDate date) {
        List<Restaurant> allRestaurants = (List<Restaurant>) restaurantRepository.findAll();
//        HashSet<Restaurant> restaurants = (HashSet<Restaurant>) restaurantRepository.getAllWithVotesOnDate(date);

        String sql = "SELECT Rest.ID, Rest.NAME, Rest.CONTACTS, Rest.ADDRESS, COUNT(v.id) votesCount from RESTAURANTS as Rest left OUTER JOIN USER_VOTES v on  Rest.ID = v.RESTAURANT_ID and v.DATE=? GROUP BY Rest.ID";
        Query q = em.createNativeQuery(sql, "RestaurantMapping");
        q.setParameter(1, date.toString());
        List<Restaurant> restaurants = (List<Restaurant>) q.getResultList();
        return restaurants;
        //If there are null, it means that all restaurants don't have a votes on that date.
        //Lets get all restaurants
//        if (restaurants == null) return (List<Restaurant>) restaurantRepository.findAll();
//        else {
//            for (Restaurant restaurant : restaurants) {
//                restaurant.setVotesCount(restaurant.getVotes().size());
//            }
//            return restaurants;
//        }
    }

//    private List<Restaurant>setVotesOnDate(List<Restaurant> allRestaurants,List<Restaurant> restaurantsWithVotes){
//        for (Restaurant restaurant : allRestaurants) {
//            if (restaurantsWithVotes.contains(restaurant)){
//                restaurant.setVotesCount(restaurantsWithVotes.);
//            }
//        }
//    }



}
