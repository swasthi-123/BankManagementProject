package BankProject;

import java.sql.*;

public class Conn {
    String url = "jdbc:mysql://localhost:3306/";
    String username = "swasthik";
    String password = "swasthik2001";
    String database;
    Connection connection;
    String sqlString = "";
    PreparedStatement statement;

    public boolean querySuccessState;
    ResultSet re=null;
    public int getResultCount=0;

    public ResultSet executeStatement() {
        try {
            statement =  connection.prepareStatement(sqlString);
            querySuccessState = statement.execute();
            if(querySuccessState)
                re= statement.getResultSet();
            else
            {
            getResultCount = statement.getUpdateCount();
                re=null;
            }
        } catch (SQLException e) {
            System.out.println("error is " + e);
        }
        return re;
    }

    public  void setQuery(String s) {
        this.sqlString = s;
    }

    public void close() {
        try{
            connection.close();
        statement.close();
        }
        catch (Exception e)
        {
        System.out.println("error is "+e);
        }}

    public  Conn(String d)
    {
        database = d;
        url = url+database;
        try {
            connection = DriverManager.getConnection(url,username,password);
        }
        catch (Exception e)
        {
            System.out.println("error is "+e);
        }
    }
}