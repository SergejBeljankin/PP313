package com.example.springcrud.exepion_handling;

public class NoSuchPersonException extends RuntimeException{

    public NoSuchPersonException(String message) {
        super(message);
    }
}
