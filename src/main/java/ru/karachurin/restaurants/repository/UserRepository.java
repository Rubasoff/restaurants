package ru.karachurin.restaurants.repository;

import org.springframework.data.repository.CrudRepository;
import ru.karachurin.restaurants.model.User;

/**
 * Created by Денис on 15.11.2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
