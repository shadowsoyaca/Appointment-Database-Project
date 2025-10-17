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
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 10/16/2025
 ****************************************************************************************/


public abstract class Person {

    protected String first_name;
    protected String last_name;
    protected int phone;
    protected String email;

    //Parent Constructor
    public Person(String first_name, String last_name, int phone, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
    }


    //common getters
    public String getFirst_name() {return first_name;}
    public  String getLast_name() {return last_name;}
    public int getPhone() {return phone;}
    public String getEmail() {return email;}

    //common toString
    public String toString(){
        return "first name: " + first_name + "\nlast name: " + last_name + "\nphone: " + phone + "\nemail: " + email;
    }

}
