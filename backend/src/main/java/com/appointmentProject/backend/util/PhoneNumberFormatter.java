
package com.appointmentProject.backend.util;

public class PhoneNumberFormatter {


	    /**
	     * Checks whether a given phone number is valid.
	     * The number must:
	     * - Not be null
	     * - Contain exactly 10 digits
	     * - Contain only numeric characters
	     * @param phoneNumber the phone number to check
	     * @return true if valid, false otherwise
	     */
	    public static boolean isValidPhoneNumber(Integer phoneNumber) {
	        if (phoneNumber == null) {
	            System.out.println("Invalid phone number: value is null.");
	            return false;
	        }

	        String phoneStr = phoneNumber.toString();

	        // Check for exactly 10 digits
	        if (phoneStr.length() != 10) {
	            System.out.println("Invalid phone number: must be exactly 10 digits.");
	            return false;
	        }

	        // Ensure all characters are digits
	        for (char c : phoneStr.toCharArray()) {
	            if (!Character.isDigit(c)) {
	                System.out.println("Invalid phone number: contains non-numeric characters.");
	                return false;
	            }
	        }

	        return true;
	    }
}
	

