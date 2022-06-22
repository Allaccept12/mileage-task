package com.example.mileage.exception;


import com.example.mileage.exception.exceptions.*;
import com.example.mileage.exception.model.Fail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NotFoundUserException.class})
    public ResponseEntity<Fail> notFoundUserException(NotFoundUserException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Fail(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler({NotFoundPlaceException.class})
    public ResponseEntity<Fail> notFoundPlaceException(NotFoundPlaceException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Fail(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler({NotFoundReviewException.class})
    public ResponseEntity<Fail> notFoundPlaceException(NotFoundReviewException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Fail(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler({NotFoundReviewRecordException.class})
    public ResponseEntity<Fail> notFoundReviewRecordException(NotFoundReviewRecordException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Fail(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler({AlreadyWroteReviewException.class})
    public ResponseEntity<Fail> WriteReviewAlreadyException(AlreadyWroteReviewException e) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Fail(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler({UserNotHaveReviewPermissionException.class})
    public ResponseEntity<Fail> NotHaveReviewPermissionUserException(UserNotHaveReviewPermissionException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Fail(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Fail> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Fail(Objects.requireNonNull(e.getFieldError()).getDefaultMessage()));
    }

}
