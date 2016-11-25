package ru.karachurin.restaurants.util.exceptions;

/**
 * Created by Денис on 17.11.2016.
 */
public class SameDayVoteException extends RuntimeException {
    public SameDayVoteException(String message) {
        super(message);
    }
}
