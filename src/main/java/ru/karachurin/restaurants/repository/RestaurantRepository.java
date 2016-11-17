package ru.karachurin.restaurants.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.karachurin.restaurants.model.Restaurant;

import java.time.LocalDate;

/**
 * Created by Денис on 15.11.2016.
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.votes v WHERE r.id =:restaurantId AND (v.date =:date OR v IS NULL)")
    Restaurant findOneWithVotesOnDate(@Param("restaurantId") Integer restaurantId, @Param("date") LocalDate date);
}
