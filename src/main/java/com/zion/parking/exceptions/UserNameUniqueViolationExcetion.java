package com.zion.parking.exceptions;

public class UserNameUniqueViolationExcetion extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public UserNameUniqueViolationExcetion(String message) {
        super(message);
    }

}
