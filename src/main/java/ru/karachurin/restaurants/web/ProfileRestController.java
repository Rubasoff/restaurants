package ru.karachurin.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.service.UserService;


import java.net.URI;

/**
 * Created by Денис on 27.11.2016.
 */
@RestController
@RequestMapping(value = ProfileRestController.REST_URL)
public class ProfileRestController {
    static final String REST_URL = "/rest/v1/profile";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @GetMapping
    public User get() {
        int id = getCurrentUserId();
        log.info("get " + id);
        return userService.get(id);
    }

    @DeleteMapping
    public void delete() {
        int id = getCurrentUserId();
        log.info("delete " + id);
        userService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        user.setId(getCurrentUserId());
        log.info("update " + user);
        userService.update(user);
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user){
        User created = userService.save(user);
        log.info("registered "+user);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    private int getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());
        return user.getId();
    }
}
