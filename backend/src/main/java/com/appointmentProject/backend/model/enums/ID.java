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

    /**********************************************************************
     * getTable
     *
     *      A getter method that returns the Table Name associated to the
     *      provided ID abbreviation. If a user writes the call:
     *          AP.getTable();
     *      The user will be given "Appointment".
     *      NOTE: The "AP" in front cannot be a String, it needs to be an
     *      ID variable.
     * @return table name (String)
     ***********************************************************************/
    public String getTable(){return this.table;}


    /************************************************************************
     * getFromTable
     *
     *      A getter method that provides the enumerated ID variable when
     *      it is provided a table name. If the parameter provided is null
     *      or it does not exist, a null value is returned.
     * @param tableName - (String) name of an existing table.
     * @return abbreviation - (ID) enumerated ID or null.
     ************************************************************************/
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


    /***************************************************************************
     * stringToEnum
     *
     *      A getter method that provides the enumerated ID variable when it
     *      is provided a String formatted abbreviation. If the parameter
     *      is null or does not exist, a null value is returned.
     * @param id - (String) The name of the abbreviation in String format.
     * @return abbreviation - (ID) enumerated ID or null.
     ***************************************************************************/
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
