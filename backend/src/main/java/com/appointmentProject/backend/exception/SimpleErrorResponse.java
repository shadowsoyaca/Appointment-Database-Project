package com.appointmentProject.backend.exception;

/*************************************************************************************
 *  SimpleErrorResponse.java
 *
 *      A simple class that organizes the messy JSON file that Spring Boot returns after
 *      receiving an error. By creating this class the JSON file becomes cleaner and
 *      designed into the way that we desire.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/21/2025
 **************************************************************************************/
public class SimpleErrorResponse {

    private String message;
    private int status;

    public SimpleErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
