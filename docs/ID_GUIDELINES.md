ID GUIDELINES
------------------------------------------------------------------------------
For most of the models, they have an ID to take advantage of the auto-increment
feature in MySQL. For organizational reasons, the IDs will have a 2-letter prefix 
to help identify what the ID is representing and will be used in later in another 
class for simplification. It will not be applied to the Room Number, Account, or
Availability models as room number and account have other means of identification,
and availability is reliant on other models to exist. 

General Format
-------------------------------------------------------------------------
 - Two-letter prefix that identifies the type of ID and which table being referenced
 - An 8 character number sequence that is unique within their model type. 
 - If the number of characters for the auto-increment number is less than 8 characters,
    0's will automatically be assigned in front of it until the number of characters is 8.
 - EXAMPLE:
<br><br>
    &nbsp;&nbsp;Patient Table Auto-Increment ID: 1430 <br>
    &nbsp;&nbsp;Front-End Displayed Patient ID: PT00001430
<br><br>

ID Compass
-------------------------------------------------------------------------
Here is the list of ids that are being utilized in the project:

- Account >> AC 
- Appointment >> AP
- Billing >> BL
- EmergencyContact >> EC
- Insurance >> IN
- LabOrder >> LB
- Manufacturer >> MN
- Medication >> MD
- Nurse >> NR
- Patient >> PT
- Pharmacy >> PH
- Prescription >> PR
- Provider >> PV
    
