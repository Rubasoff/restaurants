package ru.karachurin.restaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static ru.karachurin.restaurants.testData.DishTestData.MENU;
import static ru.karachurin.restaurants.testData.RestaurantTestData.*;

/**
 * Created by Денис on 15.11.2016.
 */
public class RestaurantServiceTest extends AbstractServiceTest{

    @Autowired
    RestaurantService service;

    @Test
    public void get() throws Exception {
        Restaurant resultRestaurant = service.get(100008);
        assertThat(resultRestaurant, equalTo(RESTAURANT2));
    }

    @Test
    public void getWithMenu() throws Exception {
        Restaurant resultRestaurant = service.get(100004);
        assertThat(resultRestaurant, equalTo(RESTAURANT1));
        List<Dish> menu = resultRestaurant.getMenu();
        assertThat(menu.get(0), is(MENU.get(0)));
        assertThat(menu.get(1), is(MENU.get(1)));
        assertThat(menu.get(2), is(MENU.get(2)));
    }

    @Test
    public void getWithVotesOnDate() throws Exception {
        Restaurant resultRestaurant = service.getWithVotesOnDate(100004, LocalDate.parse("2016-11-17"));
        assertThat(resultRestaurant, equalTo(RESTAURANT1));
        assertEquals(resultRestaurant.getVotesCount(), 2);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100004);
        assertThat(service.getAll(), is(Arrays.asList(RESTAURANT2)));
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated);
        assertThat(updated, equalTo(service.get(RESTAURANT1_ID)));
    }

    @Test
    public void save() throws Exception {
        Restaurant created = getCreated();
        service.save(created);
        assertThat(Arrays.asList(created, RESTAURANT1, RESTAURANT2), is(service.getAll()));
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> restaurants = (List<Restaurant>) service.getAll();
        assertThat(Arrays.asList(RESTAURANT1, RESTAURANT2), is(restaurants));
    }

    @Test
    public void getAllWithVotesOnDate() throws Exception {
        List<Restaurant> restaurants = (List<Restaurant>) service.getAllWithVotesOnDate(LocalDate.parse("2016-11-17"));
        assertThat(Arrays.asList(RESTAURANT1, RESTAURANT2), is(restaurants));
        assertThat(restaurants.get(0).getVotes(), hasSize(2));
        assertThat(restaurants.get(1).getVotes(), hasSize(0));
    }

}