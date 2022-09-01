package com.backend.portfolio.exceptions;

public class UserNotFoundException extends RuntimeException{
    /**
     * @param msg
     * Send the message if the user was not found
     * @return msg
     */
    public UserNotFoundException(String msg){
        super(msg);
    }
}
