package ru.karachurin.restaurants.util.exceptions;

/**
 * Created by Денис on 15.11.2016.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
