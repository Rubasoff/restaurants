package ru.karachurin.restaurants.web;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.karachurin.restaurants.repository.UserRepository;
import ru.karachurin.restaurants.service.DishService;
import ru.karachurin.restaurants.service.RestaurantService;
import ru.karachurin.restaurants.service.UserService;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static ru.karachurin.restaurants.testData.UserTestData.ADMIN;
import static ru.karachurin.restaurants.testData.UserTestData.USER;
import static ru.karachurin.restaurants.testData.UserTestData.USER_ID;

/**
 * Created by Денис on 28.11.2016.
 */
@RunWith(SpringRunner.class)
@WithMockUser(username="User",roles={"USER"})
public class AbstractRestControllerTest {
    @MockBean
    RestaurantService restaurantService;
    @MockBean
    DishService dishService;
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void init(){
        given(userService.get(USER_ID)).willReturn(USER);
        given(userService.getByUsername("User")).willReturn(USER);
        given(userService.getAll()).willReturn(Arrays.asList(USER, ADMIN));
    }
}
