package com.appointmentProject.backend.util;
import com.appointmentProject.backend.model.enums.ID;
/**********************************************************************************************
 * IDFormatter.java
 *
 *          This file has two responsibilities:
 *
 *          1. To convert a Pre-fixed ID (ex: PT00000001) and create a query skeleton that can
 *          assist with creating full queries or allow for further dismantling for other
 *          purposes. The query skeleton will look like this:
 *
 *              "FROM <table_name> WHERE id IS <simplified_number>"
 *
 *          2. To take a simplified number and the table name it originated and convert it
 *          into a Pre-fixed ID.
 *
 *          Both purposes will use methods from the ID enum class. In addition, some of the
 *          steps will be broken into individual helper methods.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/04/2026
 *******************************************************************************************/
public class IDFormatter {

    //FORMATTER METHOD


    /***************************************************************************
     * simplifyID
     *         A main method where a Pre-fixed ID is converted into a
     *         simplified ID. It needs multiple checks and helper methods for
     *         it to function.
     * - @param: prefix-id - String (can be null)
     * - @return: String - a skeleton query containing the table and the
     *          simplified ID number. If it returns a null, then it is because
     *          the id provided is a NULL value or the number sequence does not
     *          match 8 characters long.
     *
     *
     ***************************************************************************/
    public String simplifyID(String id) {

        //Separate prefix from sequence
        String prefix = id.substring(0, 2);
        String number = id.substring(2);

        //Helper Method Validity Check
        if (!check(prefix, number)) {
            return null;
        }

        //Getting the table name.
        ID converted = ID.stringToEnum(prefix); //prefix -> enum ID
        String table = converted.getTable(); //enum ID gets tableName String

        //Transforming the Number
        int num = Integer.parseInt(number);

        //Format and Return the Skeleton Query
        return "FROM " + table + " WHERE id IS " + number;

    }

    /******************************************************************************************
     * formatPrefix
     *           A main method that converts parts of an ID and combines them into their prefix
     *           format. (EX: PT00000002)
     * @param prefix (String) - name of the table OR is an abbreviation.
     * @param id (int) - the auto-incremented id assigned to the record in the database.
     * @return ID (String) - returns the prefixed id or a null if any part is invalid.
     ******************************************************************************************/
    public String formatPrefix(String prefix, int id){
        //convert the id into an 8-digit number
        String number = String.format("%8d", id);
        String abbreviation;

        //Helper Method Validity Check
        if(check(prefix, number)){
            return null;
        }
        //getting the enum value to determine if prefix is a table or an abbreviation
        ID converted = ID.stringToEnum(prefix);

        //Prefix is a table name.
        if(converted == null) {

            //Get the Abbreviation and Converting it into a String
            abbreviation = ID.getFromTable(prefix).toString();
        }
        //Prefix is not a table name.
        else{
            abbreviation = converted.toString();
        }
        return abbreviation + number;

    }



    //HELPER METHOD
    /*******************************************************************************************
     * check
     *      A helper method that checks for Valid abbreviation, an 8-character
     *      length number, and a number sequence containing only numbers.
     * @param prefix: (String) expecting a valid abbreviation for the IDS OR a table name.
     * @param number: (String) expecting a sequence that is all numbers and has a length of 8.
     * @return boolean - true if its valid, false for invalid
     *******************************************************************************************/
    public boolean check(String prefix, String number) {
        //Abbreviation (Prefix) Check/Table Name Check
        if (ID.stringToEnum(prefix) == null) {
            //Not an abbreviation, check if prefix is actually a table name
            if(ID.getFromTable(prefix) == null){
                return false;
            }
        }

        //Number Sequence Length Check
        if (number.length() != 8) {
            return false;
        }

        //Every Character is a Number
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}