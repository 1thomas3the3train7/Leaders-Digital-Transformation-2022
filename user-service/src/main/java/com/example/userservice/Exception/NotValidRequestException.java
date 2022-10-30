package com.example.userservice.Exception;

public class NotValidRequestException extends RuntimeException{
    public NotValidRequestException() {
        super("Not valid request (null)");
        System.out.println("Not valid request (null)");
    }

    public NotValidRequestException(String message) {
        super(message);
        System.out.println(message);
    }

    public NotValidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidRequestException(Throwable cause) {
        super(cause);
    }
}
