package com.appointmentProject.backend.exception;


/****************************************************************************************
 *  RecordNotFoundException.java
 *
 *      A custom exception thrown when a record containing the specified field detail does
 *      not exist in the database.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/13/2025
 *****************************************************************************************/
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }

}
