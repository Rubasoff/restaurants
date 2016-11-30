package ru.karachurin.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.service.DishService;
import ru.karachurin.restaurants.service.RestaurantService;
import ru.karachurin.restaurants.service.UserService;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Денис on 18.11.2016.
 */
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL)
public class RestaurantRestController {
    static final String REST_URL = "/rest/v1/restaurants";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    DishService dishService;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    Restaurant get(@PathVariable int id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        log.info("get " + id);
        if (date==null){
            return restaurantService.get(id);
        }
        else {
            return restaurantService.getWithVotesOnDate(id, date);
        }
    }
    @GetMapping
    List<Restaurant> getAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        log.info("getAll");
        if (date==null){
            return (List<Restaurant>) restaurantService.getAll();
        }
        else {
            return (List<Restaurant>) restaurantService.getAllWithVotesOnDate(date);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) throws NotFoundException {
        log.info("update " + id);
        restaurantService.update(restaurant);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws NotFoundException {
        log.info("delete " + id);
        restaurantService.delete(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.save(restaurant);
        log.info("create " + created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    @GetMapping("/{id}/menu")
    List<Dish> getMenu(@PathVariable int id){
        log.info("getAll menu");
        return dishService.getAllFromRestaurant(id);
    }
    @GetMapping("/{id}/menu/{dishId}")
    Dish getDish(@PathVariable("id") int restaurantId, @PathVariable int dishId) throws NotFoundException {
        log.info("get dish "+dishId);
        return dishService.get(dishId);
    }
    @DeleteMapping("/{id}/menu/{dishId}")
    public void deleteDish(@PathVariable("id") int restaurantId, @PathVariable int dishId) throws NotFoundException {
        log.info("delete dish "+dishId);
        dishService.delete(dishId);
    }
    @PutMapping(value = "/{id}/menu/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDish(@RequestBody Dish dish, @PathVariable("id") int restaurantId, @PathVariable("id") int dishId) throws NotFoundException {
        log.info("update dish "+dish);
        dishService.update(dish, dishId, restaurantId);
    }
    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDishWithLocation(@RequestBody Dish dish, @PathVariable("id") int restaurantId) {
        Dish created = dishService.save(dish, restaurantId);
        log.info("create dish "+created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{id}/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void vote(@PathVariable("id") int restaurantId){
        log.info("vote ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.doVote(userService.getByUsername(auth.getName()), restaurantId, LocalDateTime.now());
    }
}
