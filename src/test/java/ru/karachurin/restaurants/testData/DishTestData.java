package ru.karachurin.restaurants.testData;

import ru.karachurin.restaurants.model.Dish;

import java.util.Arrays;
import java.util.List;

import static ru.karachurin.restaurants.model.BaseEntity.START_SEQ;
import static ru.karachurin.restaurants.testData.RestaurantTestData.RESTAURANT1;

/**
 * Created by Денис on 16.11.2016.
 */
public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 5;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Pasta", 40.50).setRestaurant(RESTAURANT1);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Pizza", 10.00).setRestaurant(RESTAURANT1);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Steak", 70.30).setRestaurant(RESTAURANT1);

    public static final List<Dish> MENU = Arrays.asList(DISH1, DISH2, DISH3);

    public static Dish getCreated(){
        return new Dish(null, "New dish", 60.d);
    }

    public static Dish getUpdated(){
        return new Dish(DISH1_ID, "Updated dish", 50.d).setRestaurant(RESTAURANT1);
    }
}
