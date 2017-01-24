package ru.karachurin.restaurants.web;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.web.json.JsonUtil;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.karachurin.restaurants.testData.UserTestData.ADMIN_ID;
import static ru.karachurin.restaurants.testData.UserTestData.USER;
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
        mockMvc.perform(get(REST_URL+USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andDo(print());
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        mockMvc.perform(post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(USER)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL+USER_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER_ID, "newName", "newemail@ya.ru", "newPassword");
        mockMvc.perform(put(REST_URL+USER_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByUsername() throws Exception {
        mockMvc.perform(get(REST_URL+"/by").param("username", "User"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andDo(print());
    }

}