package ru.karachurin.restaurants.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.karachurin.restaurants.model.User;

/**
 * Created by Денис on 15.11.2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT u FROM User u WHERE u.email=?1")
    User getByEmail(String email);
}
