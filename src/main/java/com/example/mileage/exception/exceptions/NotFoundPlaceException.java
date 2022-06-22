package com.example.mileage.exception.exceptions;

import com.example.mileage.exception.ErrorCode;
import lombok.Getter;

public class NotFoundPlaceException extends RuntimeException{

    public NotFoundPlaceException() {
        super();
    }

    public NotFoundPlaceException(String message) {
        super(message);
    }

    @Getter
    private ErrorCode errorCode;

    public NotFoundPlaceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
