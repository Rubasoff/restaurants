package ru.karachurin.restaurants.testData;

import ru.karachurin.restaurants.model.Restaurant;

import static ru.karachurin.restaurants.model.BaseEntity.START_SEQ;

/**
 * Created by Денис on 16.11.2016.
 */
public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Pushkin", "+7 937 777 777", "Pushkina st. 53");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Gogol", "+7 937 234 111", "Gogol st. 40");
}
