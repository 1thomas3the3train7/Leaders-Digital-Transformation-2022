package com.example.userservice.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found");
        System.out.println("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
