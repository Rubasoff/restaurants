package ru.karachurin.restaurants.service;




import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static ru.karachurin.restaurants.testData.DishTestData.*;
import static ru.karachurin.restaurants.testData.RestaurantTestData.RESTAURANT1_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;


import java.util.Arrays;
import java.util.List;


/**
 * Created by Денис on 15.11.2016.
 */

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Test
    public void get() throws Exception {
        Dish resultDish = service.get(DISH1_ID);
        assertThat(resultDish, equalTo(DISH1));
    }

    @Test
    public void delete() throws Exception {
        service.delete(DISH1_ID);
        assertThat(service.getAll(), is(Arrays.asList(DISH2, DISH3, DISH4)));
    }

    @Test
    public void deleteAll() throws Exception {
        service.deleteAll();
        assertThat(service.getAll(), is(empty()));
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, updated.getId(), updated.getRestaurant().getId());
        assertThat(updated, equalTo(service.get(DISH1_ID)));
    }

    @Test
    public void save() throws Exception {
        Dish created = getCreated();
        service.save(created, RESTAURANT1_ID);
        assertThat(Arrays.asList(DISH1, DISH2, DISH3, DISH4, created), is(service.getAll()));
    }

    @Test
    public void getAllFromRestaurant() throws Exception {
        List<Dish> allDishes = (List<Dish>) service.getAllFromRestaurant(RESTAURANT1_ID);
        assertThat(allDishes, is(MENU));
    }

}