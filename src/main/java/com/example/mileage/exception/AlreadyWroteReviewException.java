package com.example.mileage.exception;

public class AlreadyWroteReviewException extends RuntimeException {

    public AlreadyWroteReviewException() {
    }

    public AlreadyWroteReviewException(String message) {
        super(message);
    }
}
