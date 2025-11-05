/*****************************************************************************************
 * Person.java
 *
 *      This class is a parent class to many of the person related
 *      object fields. As a lot of variables are the same across
 *      models, it is more efficient to create a singular parent
 *      model to hold the duplicate data instead.
 *
 *      "first_person": the first name of the person
 *      "last_name": the last name of the person
 *      "phone" : preferred phone number of the person
 *      "email" : preferred email of the person
 *
 *      Depending on the Person subclass, phone and email could be
 *      optional; but nulls can still pass through the Parent class.
 *      - The import for NullString is applied to display nulls as "N/A".
 *
 * @author Matthew Kiyono
 * @version 1.1
 * @since 10/16/2025
 ****************************************************************************************/

package com.appointmentProject.backend.abstractmodel;
import com.appointmentProject.backend.util.NullString;

public abstract class Person {

    private String first_name;
    protected String last_name;
    protected String phone;
    protected String email;

    //Parent Constructor
    public Person(String first_name, String last_name, String phone, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
    }


    //common getters
    public String getFirst_name() {return first_name;}
    public  String getLast_name() {return last_name;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}

    //common setters
    public void setFirst_name(String first_name) {this.first_name = first_name;}
    public void setLast_name(String last_name) {this.last_name = last_name;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setEmail(String email) {this.email = email;}

    //common toString
    public String toString(){
        return "first name: " + first_name +
                "\nlast name: " + last_name +
                "\nphone: " + phone +
                "\nemail: " + NullString.check(email);
    }

}
