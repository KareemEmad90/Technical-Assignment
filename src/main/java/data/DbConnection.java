package data;

import java.sql.*;

public class DbConnection {

    String Username, password;
    Connection connection;
    Statement statement;
    public void closeConnection (){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void closeStatement(Statement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // step2 create the connection object
        try {
            connection = DriverManager.getConnection(getDatabaseConnection(), Username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("connected to DB   --  ");

    }


    private String getDatabaseConnection() {
        String connection = LoadProperties.userData.getProperty("DBConn");
                Username = LoadProperties.userData.getProperty("DBUserName");
                password = LoadProperties.userData.getProperty("DBPassword");

        return connection;
    }
    public void executeUpdateStatement(String sqlStatement){
       setConnection();

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // step4 execute query
        int result  = 0;
        try {
            System.out.println(sqlStatement);
            result = statement.executeUpdate(sqlStatement);
            System.out.println("Updated rows : " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }
    public String executeStatement(String sqlStatement)  {
        setConnection();
        ResultSet result = null;
        Statement statement = null;
        String  resultvalue = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // step4 execute query
        try {
            System.out.println(sqlStatement);
             result = statement.executeQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int countrow = 0;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            countrow++;

            try {
                resultvalue = result.getString(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (countrow == 0) {
            System.out.println(" No property");
        }



    return  resultvalue ;
    }
    public ResultSet executeSelect(String sqlStatement)  {
        setConnection();
        ResultSet result = null;
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // step4 execute query
        try {
            System.out.println(sqlStatement);
            result = statement.executeQuery(sqlStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  result ;

    }


    }

