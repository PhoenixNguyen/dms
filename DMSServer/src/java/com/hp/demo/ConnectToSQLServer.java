/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.demo;

/**
 *
 * @author HP
 */
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToSQLServer {
 
  public static void main(String[] args) {

    Connection connection = null;
    try {

  // Load the NetDirect JDBC driver

  String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

  Class.forName(driverName);


  // Create a connection to the database

  String serverName = "localhost";

  String serverPort = "1433";

  String database = serverName + ":" + serverPort;

  String url = "jdbc:sqlserver://localhost:1433;databaseName=DMSServer";

  String username = "sa";

  String password = "root";

  connection = DriverManager.getConnection(url, username, password);

  

  System.out.println("Successfully Connected to the database!");

  
    } catch (ClassNotFoundException e) {

  System.out.println("Could not find the database driver " + e.getMessage());
    } catch (SQLException e) {

  System.out.println("Could not connect to the database " + e.getMessage());
    }

 }

}


/*

Open SQL Server Configuration Manager, and then expand SQL Server 2012 Network Configuration.
Click Protocols for InstanceName, and then make sure TCP/IP is enabled in the right panel and double-click TCP/IP.
On the Protocol tab, notice the value of the Listen All item.
Click the IP Addresses tab: If the value of Listen All is yes, the TCP/IP port number for this instance of SQL Server 2012 is the value of the TCP Dynamic Ports item under IPAll. If the value of Listen All is no, the TCP/IP port number for this instance of SQL Server 2012 is the value of the TCP Dynamic Ports item for a specific IP address.
Make sure the TCP Port is 1433.
Click OK.

to column is: 1433

*/