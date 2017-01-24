package ru.karachurin.restaurants.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.karachurin.restaurants.model.Dish;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.repository.UserRepository;
import ru.karachurin.restaurants.service.DishService;
import ru.karachurin.restaurants.service.RestaurantService;
import ru.karachurin.restaurants.service.UserService;
import ru.karachurin.restaurants.testData.DishTestData;
import ru.karachurin.restaurants.web.json.JsonUtil;


import javax.annotation.PostConstruct;


import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.karachurin.restaurants.testData.DishTestData.DISH1_ID;
import static ru.karachurin.restaurants.testData.RestaurantTestData.*;

/**
 * Created by Денис on 25.11.2016.
 */
@WebMvcTest(RestaurantRestController.class)
public class RestaurantRestControllerTest extends AbstractRestControllerTest{

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
         mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @WithMockUser(username="Admin",roles={"ADMIN"})
    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
    }
    @WithMockUser(username="Admin",roles={"ADMIN"})
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @WithMockUser(username="Admin",roles={"ADMIN"})
    @Test
    public void testCreateWithLocation() throws Exception {
        Restaurant created = getCreated();
        created.setId(123);
        given(restaurantService.save(created)).willReturn(created);
        mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetMenu() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID+"/menu"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDish() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID+"/menu/"+DISH1_ID))
                .andExpect(status().isOk());
        String test;
    }
    @WithMockUser(username="Admin",roles={"ADMIN"})
    @Test
    public void testDeleteDish() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID+"/menu/"+DISH1_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @WithMockUser(username="Admin",roles={"ADMIN"})
    @Test
    public void testUpdateDish() throws Exception {
        Dish updated = DishTestData.getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID+"/menu/"+DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
    }
    @WithMockUser(username="Admin",roles={"ADMIN"})
    @Test
    public void testCreateDishWithLocation() throws Exception {
        Dish created = DishTestData.getCreated();
        created.setId(123);
        given(dishService.save(created, RESTAURANT1_ID)).willReturn(created);
        mockMvc.perform(post(REST_URL + RESTAURANT1_ID+"/menu/").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());
    }
    @Test
    public void testVote() throws Exception {
        mockMvc.perform(post(REST_URL + "/100100/vote").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}