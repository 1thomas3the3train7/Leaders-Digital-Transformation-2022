package com.example.ideaservice.Exception;

public class IdeaNotFoundException extends RuntimeException{
    public IdeaNotFoundException() {
        super();
    }

    public IdeaNotFoundException(String message) {
        super(message);
    }

    public IdeaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdeaNotFoundException(Throwable cause) {
        super(cause);
    }
}
