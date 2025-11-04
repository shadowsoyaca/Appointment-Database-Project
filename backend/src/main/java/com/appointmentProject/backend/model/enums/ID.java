package com.appointmentProject.backend.model.enums;

/***************************************************************************************
 * ID.java
 *
 *      This is a class that defines valid ID abbreviations and determines the table
 *      the record originated. If provided the enum ID, it can return a tablename that
 *      can be used in queries. If provided a tablename, it can return the enum ID. If the
 *      String version of the abbreviation is provided, then an enum ID can be returned. To
 *      get the String version of an ID, simply use <ID>.toString().
 *      - "table": represents the table the abbreviation originates from.
 *
 *      Sidenote: the getFromTable(tableName) and getFromString(id) methods can be used as a
 *          validity check to determine if the ID type matches what we currently have.
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/04/2026
 *******************************************************************************************/

public enum ID {
    AP("Appointment"),
    BL("Billing"),
    EC("EmergencyContact"),
    IN("Insurance"),
    LB("LabOrder"),
    MN("Manufacturer"),
    MD("Medication"),
    NR("Nurse"),
    PT("Patient"),
    PH("Pharmacy"),
    PR("Prescription");

    private String table;

    //constructor
    ID(String table){
        this.table = table;
    }
//getter methods
    //method to get the table (can also be used as a way to check for validity?)
    public String getTable(){return this.table;}

    //method for getting the abbreviation from the table name
    public static ID getFromTable(String tableName){
        if(tableName == null) return null;
        tableName = tableName.trim();
        for(ID id: ID.values()){
            if(id.getTable().equalsIgnoreCase(tableName)){
                return id;
            }
        }
        return null;
    }

    //method for converting a String abbreviation into the enum ID returns null for null and invalid entries
    public static ID stringToEnum(String id){
        if(id == null)return null;
        try{
            return ID.valueOf(id.trim().toUpperCase()); //trim to get rid of excess whitespace
        }catch(IllegalArgumentException e){
            return null;
        }
    }


}
