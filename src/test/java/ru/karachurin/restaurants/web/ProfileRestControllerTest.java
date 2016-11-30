package ru.karachurin.restaurants.web;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.web.json.JsonUtil;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.karachurin.restaurants.testData.UserTestData.CREATED;
import static ru.karachurin.restaurants.testData.UserTestData.USER;
import static ru.karachurin.restaurants.testData.UserTestData.USER_ID;

/**
 * Created by Денис on 28.11.2016.
 */
@WebMvcTest(ProfileRestController.class)
public class ProfileRestControllerTest extends AbstractRestControllerTest {
    private static final String REST_URL = ProfileRestController.REST_URL;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(USER_ID)))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER_ID, "newName", "newemail@ya.ru", "newPassword");
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post(REST_URL + "/registration").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(CREATED)))
                .andExpect(status().isCreated());
    }

}