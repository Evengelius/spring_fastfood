package com.main.fastfood.errors;

import lombok.Getter;

import java.util.Date;

@Getter
public class CustomErrorDetails {

    private final Date timestamp;
    private final String message;
    private final String errorMessage;

    public CustomErrorDetails(Date timestamp, String message, String errorMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
