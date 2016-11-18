package ru.karachurin.restaurants.repository;

import org.springframework.data.repository.CrudRepository;
import ru.karachurin.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */
public interface VoteRepository extends CrudRepository<Vote, Integer>{
    List<Vote> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date);
}
