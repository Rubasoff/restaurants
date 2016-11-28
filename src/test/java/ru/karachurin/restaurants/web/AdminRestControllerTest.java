package ru.karachurin.restaurants.web;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.karachurin.restaurants.testData.UserTestData.ADMIN_ID;
import static ru.karachurin.restaurants.testData.UserTestData.USER_ID;

/**
 * Created by Денис on 27.11.2016.
 */
@WebMvcTest(AdminRestController.class)
public class AdminRestControllerTest extends AbstractRestControllerTest {
    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(USER_ID)))
                .andExpect(jsonPath("$[1].id", is(ADMIN_ID)))
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testCreateWithLocation() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGetByUsername() throws Exception {

    }

}