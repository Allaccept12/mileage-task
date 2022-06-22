package com.example.mileage.exception.exceptions;

import com.example.mileage.exception.ErrorCode;
import lombok.Getter;

public class AlreadyWroteReviewException extends RuntimeException {

    public AlreadyWroteReviewException() {
    }

    public AlreadyWroteReviewException(String message) {
        super(message);
    }

    @Getter
    private ErrorCode errorCode;

    public AlreadyWroteReviewException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
