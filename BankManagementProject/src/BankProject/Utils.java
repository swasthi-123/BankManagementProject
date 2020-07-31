package BankProject;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;

class Utils {

    public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public static String getPin(String accNo, String database) {
        String pin = "";
        try {
            Conn conn = new Conn(database);
            String query = "select pinNo from Accounts where AccNo = " + accNo;
            conn.setQuery(query);
            ResultSet solution = conn.executeStatement();
            if (conn.querySuccessState) {
                while (solution.next()) {
                    pin = solution.getString("pinNo");
                }
            }
            return pin;
        } catch (Exception e) {
            System.out.println("error is " + e);
        }
        return pin;
    }

    public static String getAdminPassword(String username,String database)
    {
        String password = "";
        try {
            Conn conn = new Conn(database);
            String query = "select password from EmployeePassword where employeeId = " + username;
            conn.setQuery(query);
            ResultSet solution = conn.executeStatement();
            if (conn.querySuccessState) {
                while (solution.next()) {
                    password = solution.getString("password");
                }
            }
            return password;
        } catch (Exception e) {
            System.out.println("error is " + e);
        }
        return password;
    }

    public void createTables(String d)
    {
        Conn c1 = new Conn("");
        ArrayList<String> fullTableList = new ArrayList<>();
        fullTableList.add("Accounts");
        fullTableList.add("Balance");
        fullTableList.add("Transactions");
        fullTableList.add("PersonalDetails");
        fullTableList.add("EmployeePassword");
        fullTableList.add("EmployeeDetails");
        ArrayList<String> missingTables = new ArrayList<>();
        ArrayList<String> databaseTableList = new ArrayList<>();
        ResultSet rs;
        try {
            c1.setQuery("SHOW DATABASES LIKE '"+ d +"'");
            rs = c1.executeStatement();
            int dbcount = 0;
            while (rs.next()) {
                dbcount++;
            }
            System.out.println("dbcount is "+dbcount);
            if (dbcount == 0) {
                c1.setQuery("CREATE DATABASE "+d);
                c1.executeStatement();
                if (!c1.querySuccessState && c1.getResultCount == 1) {
                    System.out.println("created a database "+d);
                }
            } else {
                System.out.println(d + "database exists");
            }
                c1 = new Conn(d);
                c1.setQuery("Show tables");
                rs = c1.executeStatement();
                System.out.println("showing tables of "+ d);
                while (rs.next()) {
                    databaseTableList.add(rs.getString(1));
                }
                for (String s : fullTableList) {
                    if (!databaseTableList.contains(s)) {
                        missingTables.add(s);
                    }
                }
                if (missingTables.size() == 0) {
                    System.out.println("No missing tables");
                }
                System.out.println("inserting missing tables");
                for (String s : missingTables) {
                    switch (s) {
                        case "Transactions":
                            c1.setQuery("create table Transactions( id int auto_increment primary key, AccNo varchar(16) not null, date TIMESTAMP not null, type char(1) not null, Amount int not null,description varchar(50) not null ,foreign key(AccNo) references Accounts(AccNo))");
                            c1.executeStatement();
                            if (!c1.querySuccessState) {
                                System.out.println("created table Transactions");
                            } else {
                                System.out.println("Failed to create Transactions table");
                            }
                            break;
                        case "Accounts":
                            c1.setQuery(" create table Accounts( AccNo varchar(16) primary key, pinNo varchar(10) not null, Acctype varchar(10) not null, services varchar(100) not null, foreign key(AccNo) references PersonalDetails(AccNo))");
                            c1.executeStatement();
                            if (!c1.querySuccessState) {
                                System.out.println("created table Accounts");
                            } else {
                                System.out.println("Failed to create Accounts table");
                            }
                            break;
                        case "Balance":
                            c1.setQuery("create table Balance ( AccNo varchar(16) primary key, Balance int , foreign key(AccNo) references Accounts(AccNo)) ");
                            c1.executeStatement();
                            if (!c1.querySuccessState) {
                                System.out.println("created table Balance");
                            } else {
                                System.out.println("Failed to create Balance table");
                            }
                            break;
                        case "PersonalDetails":
                            c1.setQuery("create table PersonalDetails( AccNo varchar(16) primary key not null, name varchar(30) not null, fName varchar(30) not null, DOB date not null, gender varchar(10) not null, email varchar(320) , marriedStatus varchar(10) not null, address1 varchar(50) not null, address2 varchar(50), city varchar(30) not null, pinCode int not null, state varchar(30) not null, religion varchar(30) not null, category varchar(30) not null, income varchar(50) not null, qualification varchar(30) not null, pan varchar(20) not null, aadhaarNumber varchar(12) not null, seniorCitizen int(1) not null, existingAccount int(1) not null)");
                            c1.executeStatement();
                            if (!c1.querySuccessState) {
                                System.out.println("created table PersonalDetails");
                            } else {
                                System.out.println("Failed to create PersonalDetails table");
                            }
                            break;
                        case "EmployeePassword":
                            c1.setQuery("create table EmployeePassword( employeeId int auto_increment not null primary key,password varchar(16) not null ,admin int(1) default 0)");
                            c1.executeStatement();
                            if(!c1.querySuccessState)
                            {
                                System.out.println("created table EmployeePassword");
                            }
                            else
                            {
                                System.out.println("Failed to create table EmployeePassword");
                            }
                            break;
                        case "EmployeeDetails":
                            c1.setQuery("create table EmployeeDetails( employeeId int auto_increment primary key not null, name varchar(16) not null, DOB date not null, gender varchar(10) not null, email varchar(320) , designation varchar(20) not null null)");
                            c1.executeStatement();
                            if(!c1.querySuccessState)
                            {
                                System.out.println("created table EmployeeDetails");
                            }
                            else
                            {
                                System.out.println("Failed to create table EmployeeDetails");
                            }
                            break;
                    }
                }
            c1.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error is " + e);
        }
    }
}

