package com.stackroute.moviesNew.exceptions;

public class MovieNameExistsException extends Exception {

    private String message ;

    public MovieNameExistsException(){

    }

    public MovieNameExistsException(String message){
        super(message);
        this.message=message;
    }
}
