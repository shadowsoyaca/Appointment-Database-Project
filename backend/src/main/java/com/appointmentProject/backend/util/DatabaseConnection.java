package com.appointmentProject.backend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***************************************************************************************************
 *  DatabaseConnection.java
 *
 *          This creates the connection between the backend and the database. It will contain the
 *          username and password to get into the database along with the URL to the Database.
 *
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/4/2025
 ***************************************************************************************************/


public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/appointmentproject";
    private static final String USER = "root";
}
