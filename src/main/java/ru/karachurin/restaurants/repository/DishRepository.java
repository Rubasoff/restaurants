package ru.karachurin.restaurants.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Dish;

/**
 * Created by Денис on 15.11.2016.
 */
public interface DishRepository extends CrudRepository<Dish, Integer> {
}
