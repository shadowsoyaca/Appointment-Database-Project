package com.appointmentProject.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*************************************************************************************************
 * GlobalExceptionHandler.java
 *
 *      A centralized exception handler responsible for catching custom and framework exceptions
 *      thrown across the entire backend. The purpose of this handler is to convert Java exceptions
 *      into structured and meaningful HTTP responses for the frontend.
 *
 *      This class prevents code duplication across controllers and ensures that all error
 *      responses follow a consistent JSON format.
 *
 *      NOTE: @RestControllerAdvice makes this class automatically scan and intercept exceptions
 *      thrown by ANY controller within the backend.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/21/2025
 *************************************************************************************************/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*********************************************************************************************
     * Handles cases where a requested record does not exist in the database.
     *
     * Example:
     *      - Service attempts to update a record that is not in the database
     *      - Service attempts to find a record by ID, but the ID doesn't exist
     *
     * Returns HTTP 404 (NOT FOUND) along with a JSON body containing:
     *      - error message
     *      - status code
     *********************************************************************************************/
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handleRecordNotFound(RecordNotFoundException ex) {
        SimpleErrorResponse error = new SimpleErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /*********************************************************************************************
     * Handles ANY unexpected runtime error that isn't covered by a custom exception.
     *
     * Returns HTTP 500 (INTERNAL SERVER ERROR).
     *********************************************************************************************/
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<SimpleErrorResponse> handleRuntimeException(RuntimeException ex) {

        ex.printStackTrace();

        SimpleErrorResponse error = new SimpleErrorResponse(
                "An unexpected error occurred.",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
