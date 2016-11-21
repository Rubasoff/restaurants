package ru.karachurin.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.service.RestaurantService;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.time.LocalDate;

/**
 * Created by Денис on 18.11.2016.
 */
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL)
public class RestaurantRestController {
    static final String REST_URL = "/rest/v1/restaurants";

    @Autowired
    RestaurantService service;

    @GetMapping("/{id}")
    Restaurant get(@PathVariable int id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        if (date==null){
            Restaurant restaurant =  service.get(id);
            restaurant.setVotes(null);
            return restaurant;
        }
        else {
            return service.getWithVotesOnDate(id, date);
        }
    }


}
