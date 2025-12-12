<h1>Appointment Project</h1>

<h3>Project Description:</h3>

The goal of the project is to create a program that can create appointments between Patients and Providers<br>
without them overlapping with existing appointments with the same doctor or room. In addition, the appointment<br>
would hold relevant information involving lab orders, the transaction of a bill, and prescriptions prescribed<br>
to the patient in the appointment. The project is also intended to hold basic information involving insurance<br>
companies, manufacturers and their medications, pharmacies, patient's emergency contacts, and nurses. In addition, <br>
there are four types of users planned for the creation of this project:<br>
1. Admin: <br>
- Has access to all tables and all features for maintanence purposes.
- Admins are the only ones to Create and Manage User Accounts.
- For most tables, they are the only ones who has the Delete function.
- Is responsible for creating, updating, and removing Medications, Pharmacies, Manufacturers, Insurances, Nurses, and Providers.
<br>
2/3. Provider/Nurses: <br><br>
- Both have the ability to View a table of the Staff, and View a listing of Pharmacies and Medications.<br>
- Both have the ability to Update and View Patients, Lab Orders, Appointments, and Prescriptions.<br>
- Providers can Create and Remove Lab Orders and Prescriptions.
<br><br>
3. Receptionist: <br><br>
- Has the ability to Create, Update, and View Appointments, Billing, Emergency Contacts, and Patients.<br>
- Has the ability to View Prescriptions and Lab Orders.


<h3>How to Install Maven (necessary to function)</h3>
1. Ensure that you have maven installed on your computer in order to run this. If not download the binary<br>
 zip archive (not the source zip) from this website: https://maven.apache.org/download.cgi <br><br>
2. Extract the files and add Maven to your computer's PATH in the settings.<br><br>
3. To check to see if it installed, type "mvn -v" into the terminal. You should see something along the lines of:<br><br>

  Apache Maven 3.9.6<br>
  Java version: 21<br><br>

<h3>How to Run the Application</h3>
1. When you have the database installed and accessible, type in the schema and activate: <br>
Insert into useraccount(username, password, email, user_type)<br>
Values("[preferred_username]", "[preferred_password]", "[personal_email]", "[user_type]");<br><br>

user_type options:<br>
ADMIN<br>
PROVDIER<br>
NURSE<br>
RECEPTIONIST<br>

2. Open up a terminal and from the root directory, type in: "cd backend"<br>
3. From there, type in "mvn clean spring-boot:run" and wait until you see the message: "Database Connected Successfully!"<br>
4. Open up a second terminal and from the root directory, type in: "cd desktop-client" <br>
5. From there, type in "mvn clean javafx:run" <br>
6. Log into your acccount with the username and password you inserted into the database.
  
