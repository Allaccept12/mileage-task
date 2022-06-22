package com.example.mileage.exception.exceptions;

import com.example.mileage.exception.ErrorCode;
import lombok.Getter;

public class UserNotHaveReviewPermissionException extends RuntimeException{
    public UserNotHaveReviewPermissionException() {
        super();
    }

    public UserNotHaveReviewPermissionException(String message) {
        super(message);
    }

    @Getter
    private ErrorCode errorCode;

    public UserNotHaveReviewPermissionException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
