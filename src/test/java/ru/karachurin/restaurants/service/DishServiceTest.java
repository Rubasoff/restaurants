package ru.karachurin.restaurants.service;

import static org.hamcrest.Matchers.equalTo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;


import java.time.LocalDate;



/**
 * Created by Денис on 15.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/db/populateDB", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {

    @Autowired
    DishService service;

    @Test
    public void get() throws NotFoundException {
        Restaurant restaurant = new Restaurant(100004, "Пушкин", "","", null);
        Dish dish = new Dish(100005, "Лапша", 40.50, LocalDate.parse("2016-11-16"), restaurant);
        Dish resultDish = service.get(100005);
        assertThat(resultDish, equalTo(new Dish()));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void getAllFromRestaurantOnDate() throws Exception {

    }

}