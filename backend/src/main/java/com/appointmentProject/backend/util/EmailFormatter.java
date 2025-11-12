package com.appointmentProject.backend.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailFormatter {

    /**
     * Validates if the given email address is properly formatted.
     *
     * @param email the email address to validate
     * @return true if the email is valid and uses a proper TLD, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Invalid email: value is null or empty.");
            return false;
        }

        try {
            // Create an InternetAddress object
            InternetAddress internetAddress = new InternetAddress(email);

            // Validate the email address format
            internetAddress.validate();

            return true; // No exception means it's valid
        } catch (AddressException e) {
            System.out.println("Invalid email format: " + e.getMessage());
            return false;
        }
    }
}