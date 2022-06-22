package com.example.mileage.exception.exceptions;

import com.example.mileage.exception.ErrorCode;
import lombok.Getter;


public class NotFoundUserException extends RuntimeException{

    @Getter
    private ErrorCode errorCode;

    public NotFoundUserException() {
        super();
    }

    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
