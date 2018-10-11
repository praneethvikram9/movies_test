package com.stackroute.moviesNew.exceptions;

public class NoSuchMovieException extends Exception {
    private String message;

    public NoSuchMovieException() {
    }

    public NoSuchMovieException(String message) {
        super(message);
        this.message = message;
    }
}
