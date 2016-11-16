package ru.karachurin.restaurants.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Dish;

import java.util.Collection;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */
public interface DishRepository extends CrudRepository<Dish, Integer> {
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:restaurantId")
    public List<Dish> getAllFromRestaurant(@Param("restaurantId") int restaurantId);
}
