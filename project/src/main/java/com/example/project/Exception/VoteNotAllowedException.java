package com.example.project.Exception;

public class VoteNotAllowedException extends RuntimeException{
    public VoteNotAllowedException(String message) {
        super(message);
    }
}
