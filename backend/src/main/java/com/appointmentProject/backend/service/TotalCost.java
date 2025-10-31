

/**************************************************************************************************
 *  TotalCost.java
 *
 *      This is a service class that calculates the total cost for the Billing class. It
 *      will gather the costs from the Appointment, LabOrder, and Prescription models along
 *      with the presence of the InsuranceID in each of them. For one appointment, the
 *      Insurance id can cover a lab order, appointment or prescription exclusively. What
 *      is covered by the insurance depends on if the models have the Insurance ID present
 *      in them. For this project's purpose alone, we will be assuming the Insurance will
 *      be cutting the costs of everything down by 50%. It is also important to note that
 *      the lab orders and prescriptions are optional.
 *      So the formula will look something like this:
 *
 *      total cost = appointmentCost(Insurance?) + labOrder?(Insurance?) + prescrip.?(Insurance?)
 *
 *      Note:
 *          - the question marks represent checks due to being optional.
 *          - if either labOrder or prescription are empty, it gets represented as 0.0 and there
 *              is no check on the presence of insurance.
 *          - in every model, an insurance check is made on the model. If its null it returns a 1.0
 *              otherwise it will return a 1/2.
 *          - Each step will be broken down into individual steps to make the process more readable.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 10/30/2025
 *
 ************************************************************************************************/
package com.appointmentProject.backend.service;

public class TotalCost {

    double total_cost;
    //THIS IS A SKELETON IN PROGRESS DO NOT USE YET
}
