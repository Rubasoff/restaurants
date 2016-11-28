package ru.karachurin.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.service.UserService;

import java.net.URI;
import java.util.List;

/**
 * Created by Денис on 27.11.2016.
 */
@RestController
@RequestMapping(value = AdminRestController.REST_URL)
public class AdminRestController {
    static final String REST_URL = "/rest/v1/admin/users";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        log.info("get " + id);
        return userService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        User created = userService.save(user);
        log.info("create " + created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        userService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        log.info("update " + user);
        userService.save(user);
    }

    @GetMapping(value = "/by")
    public User getByUsername(@RequestParam("username") String username) {
        log.info("username " + username);
        return userService.getByUsername(username);
    }
}
