package ru.karachurin.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.service.DishService;
import ru.karachurin.restaurants.service.RestaurantService;
import ru.karachurin.restaurants.service.UserService;
import ru.karachurin.restaurants.to.UserVoteTO;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Денис on 18.11.2016.
 */
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL)
public class RestaurantRestController {
    static final String REST_URL = "/rest/v1/restaurants";

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    DishService dishService;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    Restaurant get(@PathVariable int id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        if (date==null){
            return restaurantService.get(id);
        }
        else {
            return restaurantService.getWithVotesOnDate(id, date);
        }
    }
    @GetMapping
    List<Restaurant> getAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        if (date==null){
            return (List<Restaurant>) restaurantService.getAll();
        }
        else {
            return (List<Restaurant>) restaurantService.getAllWithVotesOnDate(date);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) throws NotFoundException {
        restaurantService.update(restaurant);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws NotFoundException {
        restaurantService.delete(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}/menu")
    List<Dish> getMenu(@PathVariable int id){
        return dishService.getAllFromRestaurant(id);
    }
    @GetMapping("/{id}/menu/{dishId}")
    Dish getDish(@PathVariable("id") int restaurantId, @PathVariable int dishId) throws NotFoundException {
        return dishService.get(dishId);
    }
    @DeleteMapping("/{id}/menu/{dishId}")
    public void deleteDish(@PathVariable("id") int restaurantId, @PathVariable int dishId) throws NotFoundException {
        restaurantService.delete(dishId);
    }
    @PutMapping(value = "/{id}/menu/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDish(@RequestBody Dish dish, @PathVariable("id") int restaurantId, @PathVariable("id") int dishId) throws NotFoundException {
        dishService.update(dish, dishId, restaurantId);
    }
    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDishWithLocation(@RequestBody Dish dish, @RequestParam("id") int restaurantId) {
        Dish created = dishService.save(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{id}/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void vote(@PathVariable("id") int restaurantId, @RequestBody UserVoteTO voteTO){
        userService.doVote(voteTO.getUserId(), voteTO.getRestaurantId(), voteTO.getDate());
    }

}
