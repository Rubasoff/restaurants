package ru.karachurin.restaurants.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.karachurin.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.votes v WHERE  v.date=:date")
    List<Restaurant> getAllWithVotesOnDate(@Param("date") LocalDate date);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.votes")
    @Override
    Iterable<Restaurant> findAll();
}
