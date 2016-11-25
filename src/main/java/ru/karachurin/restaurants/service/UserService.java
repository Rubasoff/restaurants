package ru.karachurin.restaurants.service;

import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */
public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    void enable(int id, boolean enable);

    void doVote(int userId, int restaurantId, LocalDateTime date);
}
