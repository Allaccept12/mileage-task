package com.example.mileage.exception.model;


import lombok.Data;

@Data
public class Fail {

    private boolean success = false;
    private String msg;

    public Fail(String msg) {
        this.msg = msg;
    }
}
