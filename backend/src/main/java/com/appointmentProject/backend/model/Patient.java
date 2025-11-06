/*********************************************************************************************
 * Patient.java
 *
 *      This is the blueprint for storing information related to patients and acts as a
 *      model to transfer information between frontend and the database.
 *
 *      Contains the identifying variables of the Patient and is used by the service layer
 *      for retrieval and updates.
 *      - "id": the unique identifier to differentiate between Patients. It is also the
 *             primary way of accessing specific Patient records. This is immutable and
 *             leads with a "PT". The number combination is created from the
 *             auto-increment feature from MySQL.
 *      - "first_name", "last_name", "phone", and (Optional) "email"  are inherited from
 *              Person.java.
 *      - "DoB": the patient's date of birth.
 *      - "gender": (Optional) the patient's gender identity and expression
 *      - "age": patient's age, can be 0 if they are only a few months old.
 *      - "weight": patient's weight in pounds in the format of a double.
 *      - "height": patient's height in feet in the format of a double.
 *      - "insurance_id": the patient's insurance provider. Can be null if they do not have
 *              one. The id must have a "IN" lead before the number sequence. (Optional)
 *      - "emergencyContact_id": the patient's emergency contact. Can be null if they do not
 *              have one. The id must start with "EC" before the number sequence. (Optional)
 *
 *      Designer Note: As email, gender, insurance_id, and emergency_contact_id are optional,
 *              the builder pattern constructor is used over the traditional constructor.
 *              - In addition, the import of LocalDate is used for defining the Date of Birth.
 *              - Import for NullString applied for displaying nulls as "N/A"
 *
 * @author Matthew Kiyono
 * @version 1.2
 * @since 10/20/2025
 *
 ********************************************************************************************/
package com.appointmentProject.backend.model;

import com.appointmentProject.backend.abstractmodel.Person;
import com.appointmentProject.backend.util.NullString;
import java.time.LocalDate;

public class Patient extends Person {

    //required variables
    private String id;
    private LocalDate DoB;
    private int age;
    private double weight;
    private double height;

    //optional variables
    private String gender;
    private String insurance_id;
    private String emergencyContact_id;


    //private constructor used only by builder
    private Patient(Builder builder) {
        super(builder.first_name, builder.last_name, builder.phone, builder.email);
        this.id = builder.id;
        this.DoB = builder.DoB;
        this.age = builder.age;
        this.weight = builder.weight;
        this.height = builder.height;
        this.gender = builder.gender;
        this.insurance_id = builder.insurance_id;
        this.emergencyContact_id = builder.emergencyContact_id;

    }

    //getter methods
    public String getId() {
        return id;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public String getInsurance_id() {
        return insurance_id;
    }

    public String getEmergencyContact_id() {
        return emergencyContact_id;
    }

    //setter methods
    public void setId(String id) {this.id = id;}
    public void setDoB(LocalDate  DoB) {this.DoB = DoB;}
    public void setAge(int age) {this.age = age;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setHeight(double height) {this.height = height;}
    public void setGender(String gender) {this.gender = gender;}
    public void setInsurance_id(String in_id) {this.insurance_id = in_id;}
    public void setEmergencyContact_id(String ec_id) {this.emergencyContact_id = ec_id;}

    //toString
    public String toString() {
        return "Patient:\n" +
                "Patient ID: " + id + "\n" +
                super.toString() +
                "\nDoB: " + DoB + "\n" +
                "Age: " + age + "\n" +
                "Weight: " + weight + "\n" +
                "Height: " + height + "\n" +
                "Gender: " + NullString.check(gender) + "\n" +
                "Insurance Id: " + NullString.check(insurance_id) + "\n" +
                "Emergency Contact ID: " + NullString.check(emergencyContact_id) +
                "\n";
    }

    public static class Builder {
        //required
        private String id;
        private String first_name;
        private String last_name;
        private LocalDate DoB;
        private int age;
        private double weight;
        private double height;
        private String phone;

        //optional
        private String gender;
        private String email;
        private String insurance_id;
        private String emergencyContact_id;

        //constructor that utilizes only the required variables.
        public Builder(String id, String first_name, String last_name, LocalDate DoB, int age, double weight, double height, String phone) {
            this.id = id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.phone = phone;
            this.DoB = DoB;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        //optional variables
        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder insurance_id(String in_id) {
            this.insurance_id = in_id;
            return this;
        }

        public Builder emergencyContact_id(String ec_id) {
            this.emergencyContact_id = ec_id;
            return this;
        }
        //combining provided variables
        public Patient build() {
            return new Patient(this);
        }

    }


}
