package ru.karachurin.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.service.RestaurantService;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

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
    RestaurantService service;

    @GetMapping("/{id}")
    Restaurant get(@PathVariable int id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        if (date==null){
            return service.get(id);
        }
        else {
            return service.getWithVotesOnDate(id, date);
        }
    }

    @GetMapping
    List<Restaurant> getAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        if (date==null){
            return (List<Restaurant>) service.getAll();
        }
        else {
            return (List<Restaurant>) service.getAllWithVotesOnDate(date);
        }
    }


}
