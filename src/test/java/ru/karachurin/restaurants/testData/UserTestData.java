package ru.karachurin.restaurants.testData;

import ru.karachurin.restaurants.model.User;

import java.util.Objects;

import static ru.karachurin.restaurants.model.BaseEntity.START_SEQ;


/**
 * Created by Денис on 16.11.2016.
 */
public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password");
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin");
    public static final User CREATED = new User(null, "New", "new@yandex.ru", "password");

}