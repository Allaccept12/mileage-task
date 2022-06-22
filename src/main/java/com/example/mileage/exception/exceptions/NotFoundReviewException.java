package com.example.mileage.exception.exceptions;

import com.example.mileage.exception.ErrorCode;
import lombok.Getter;

public class NotFoundReviewException extends RuntimeException{
    public NotFoundReviewException() {
        super();
    }

    public NotFoundReviewException(String message) {
        super(message);
    }

    @Getter
    private ErrorCode errorCode;

    public NotFoundReviewException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
