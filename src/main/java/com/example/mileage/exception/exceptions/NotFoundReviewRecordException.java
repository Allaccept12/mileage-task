package com.example.mileage.exception.exceptions;

import com.example.mileage.exception.ErrorCode;
import lombok.Getter;

public class NotFoundReviewRecordException extends RuntimeException{
    public NotFoundReviewRecordException() {
        super();
    }

    public NotFoundReviewRecordException(String message) {
        super(message);
    }

    @Getter
    private ErrorCode errorCode;

    public NotFoundReviewRecordException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
