package ru.karachurin.restaurants.testData;

import ru.karachurin.restaurants.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static ru.karachurin.restaurants.model.BaseEntity.START_SEQ;

/**
 * Created by Денис on 16.11.2016.
 */
public class RestaurantTestData {
    public static final int RESTAURANT1_ID = 100100;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Pushkin", "Pushkina st. 53", "+7 937 777 777");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 4, "Gogol", "Gogol st. 40", "+7 937 234 111");
    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2);

    public static Restaurant getCreated(){
        return new Restaurant(null, "Created", "st.", "111");
    }

    public static Restaurant getUpdated(){
        return new Restaurant(RESTAURANT1_ID, "Updated", "Pushkina st. 54", "+7 937 777 778");
    }
}
