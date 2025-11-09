
/***************************************************************************************
 * Billing.java
 *
 *      This is a blueprint for storing information related to Billing and acts as
 *      a model to transfer information between the backend and the frontend.
 *
 *      Contains identifying variables of Billing and is used by the service layer
 *      for retrieval and updates.
 *      - "id": is a unique identifier that differentiates between Billing transactions.
 *          It is also the primary way of accessing specific Billing records. This is
 *          immutable. The number combination is created by the auto-increment feature in MySQL.
 *      - "insurance_id": (nullable) the id for the insurance that could be covering it.
 *          If it"s NULL, it is assumed that the Patient receiving this bill does not have
 *          insurance OR the insurance company will not cover anything in the bill. This
 *          means that as long as the insurance covers something, then the insurance will
 *          show up in the bill.
 *      - "cost": the total cost of the appointment. The calculation will be conducted through
 *          a service class.
 *      - "status_of_payment": Shows the general progress of payment completion.
 *          - Acceptable Payment Statuses:
 *              - "PAID": the bill has been paid off.
 *              - "PENDING": the bill is still being processed or is awaiting confirmation
 *              - "INCOMPLETE": the full balance of the bill is due
 *              - "WAIVED": financial responsibility has been forgiven due to settlement or
 *                      financial hardship.
 *              - "PARTIAL": some of the payment is paid for by insurance or the patient, and
 *                      the remainder is still needing to be paid off.
 *      - "payment_type": the payment type in which the patient pays
 *          the bill.
 *          - Acceptable Payment Types:
 *              - "CREDIT": credit card
 *              - "CHECK": check
 *              - "DEBIT": debit
 *              - "CASH": cash
 *              - "DIGITAL": digital wallet
 *              - "INSURANCE": insurance covers the full thing
 *              - "N/A": Only Available if Waived or Incomplete is the current status.
 *          - Additional NOTE: if the Insurance ID is not null and the acceptable payment
 *                  type is anything but INSURANCE, this implies that the Insurance will
 *                  be covering a portion of it. Also, if there is no Insurance ID in the
 *                  assigned Appointment's Insurance ID or its assigned LabOrders or
 *                  Prescription's Insurance IDs, then the Insurance ID cannot be present
 *                  on the current billing. This will be handled in a separate service class.
 *
 * @author Matthew Kiyono
 * @version 1.3
 * @since 10/30/2026
 *******************************************************************************************/
package com.appointmentProject.backend.model;
import com.appointmentProject.backend.util.NullString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Billing {

    //required variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double cost;
    private String status_of_payment;
    private String payment_type;

    //optional variables
    private Integer insurance_id;

    //constructor
    private Billing(Builder builder){
        this.id = builder.id;
        this.cost = builder.cost;
        this.status_of_payment = builder.status_of_payment;
        this.payment_type = builder.payment_type;
        this.insurance_id = builder.insurance_id;
    }

    //getter method
    public int getId() {return id;}
    public double getCost() {return cost;}
    public String getStatus_of_payment() {return status_of_payment;}
    public String getPayment_type() {return payment_type;}
    public Integer getInsurance_id() {return insurance_id;}

    //setter method
    public void setId(int id) {this.id = id;}
    public void setCost(double cost) {this.cost = cost;}
    public void setStatus_of_payment(String status_of_payment) {this.status_of_payment = status_of_payment;}
    public void setPayment_type(String payment_type) {this.payment_type = payment_type;}
    public void setInsurance_id(Integer insurance_id) {this.insurance_id = insurance_id;}

    //toString method
    @Override
    public String toString(){
        return "Billing: " +
                "\nTransaction ID: " + id +
                "\nTotal Cost: " + cost +
                "\nPayment Status: " + status_of_payment +
                "\nPayment Type: " + payment_type +
                "\nInsurance ID: " + NullString.check(insurance_id) + "\n";
    }

    //builder constructor
    public static class Builder{

        //required variables
        private int id;
        private double cost;
        private String status_of_payment;
        private String payment_type;
        //optional variables
        private Integer insurance_id;

        //required parameter constructor
        public Builder(int id, double cost,String status_of_payment,String payment_type){
            this.id = id;
            this.cost = cost;
            this.status_of_payment = status_of_payment;
            this.payment_type = payment_type;
        }

        //optional parameter checks
        public Builder insurance_id(Integer insurance_id){
            this.insurance_id = insurance_id;
            return this;
        }

        //putting them together
        public Billing build(){return new Billing(this);}
    }


}
