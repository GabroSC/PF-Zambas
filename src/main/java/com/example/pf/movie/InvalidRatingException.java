package com.example.pf.movie;

public class InvalidRatingException extends RuntimeException {

    public InvalidRatingException(String message) {
        super(message);
    }
}
