package data;

import com.shaft.tools.io.PropertyFileManager;
import com.shaft.tools.io.ReportManager;
import org.testng.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseActionsCustom {

    private String username = LoadProperties.userData.getProperty("DBUserName");
    private String password =  LoadProperties.userData.getProperty("DBPassword");
    private String connectionString = "";


    public static String getResult(ResultSet resultSet) {
        String resultSetString = getResultStringValue(resultSet, false);
        passAction();
        return resultSetString;
    }

    public static String getRow(ResultSet resultSet, String columnName, String knownCellValue) {
        String reportMessage = "Column Name: \"" + columnName + "\" | Cell Content: \"" + knownCellValue + "\"";
        StringBuilder str = new StringBuilder();
        boolean foundRow = false;

        try {
            resultSet.beforeFirst();
            if (resultSet.last()) {
                int columnsCount = resultSet.getMetaData().getColumnCount();
                int lastRowID = resultSet.getRow();
                int targetColumnID = resultSet.findColumn(columnName);

                for(int i = 1; i <= lastRowID; ++i) {
                    resultSet.absolute(i);
                    if (String.valueOf(resultSet.getString(targetColumnID)).trim().equals(knownCellValue.trim())) {
                        for(int j = 1; j <= columnsCount; ++j) {
                            str.append(resultSet.getString(j)).append("\t");
                        }

                        str.append("\n");
                        foundRow = true;
                    }
                }
            }
        } catch (NullPointerException | SQLException var11) {
            ReportManager.log(String.valueOf(var11));
            failAction(reportMessage, var11);
        }

        if (Boolean.TRUE.equals(foundRow)) {
            passAction(reportMessage);
        } else {
            failAction(reportMessage);
        }

        return str.toString().trim();
    }

    public static String getColumn(ResultSet resultSet, String columnName) {
        StringBuilder str = new StringBuilder();

        try {
            resultSet.beforeFirst();
            if (resultSet.last()) {
                int lastRowID = resultSet.getRow();
                int targetColumnID = resultSet.findColumn(columnName);

                for(int i = 1; i <= lastRowID; ++i) {
                    resultSet.absolute(i);
                    str.append(resultSet.getString(targetColumnID)).append("\n");
                }
            }
        } catch (NullPointerException | SQLException var6) {
            ReportManager.log(String.valueOf(var6));
            failAction(var6);
        }

        passAction(columnName);
        return str.toString().trim();
    }

    public static int getRowCount(ResultSet resultSet) {
        int rowCount = 0;

        try {
            resultSet.beforeFirst();
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst();
            }
        } catch (SQLException var3) {
            ReportManager.log(String.valueOf(var3));
            failAction(var3);
        }

        passAction();
        return rowCount;
    }

    private static void passAction(String actionName, String testData, String queryResult) {
        reportActionResult(actionName, testData, queryResult, true);
    }

    private static void passAction(String testData, String queryResult) {
        String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        passAction(actionName, testData, queryResult);
    }

    private static void passAction(String testData) {
        String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        passAction(actionName, testData, (String)null);
    }

    private static void passAction() {
        String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        passAction(actionName, (String)null, (String)null);
    }

    private static void failAction(String actionName, String testData, Exception... rootCauseException) {
        String message = reportActionResult(actionName, testData, (String)null, false);
        if (rootCauseException != null && rootCauseException.length >= 1) {
            Assert.fail(message, rootCauseException[0]);
        } else {
            Assert.fail(message);
        }

    }

    private static void failAction(String testData, Exception... rootCauseException) {
        String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        failAction(actionName, testData, rootCauseException);
    }

    private static void failAction(Exception... rootCauseException) {
        String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        failAction(actionName, (String)null, rootCauseException);
    }

    private static String reportActionResult(String actionName, String testData, String queryResult, Boolean passFailStatus) {
        String message;
        if (Boolean.TRUE.equals(passFailStatus)) {
            message = "Database Action [" + actionName + "] successfully performed.";
        } else {
            message = "Database Action [" + actionName + "] failed.";
        }

        List<List<Object>> attachments = new ArrayList();
        if (testData != null && !testData.isEmpty() && testData.length() >= 500) {
            List<Object> actualValueAttachment = Arrays.asList("Database Action Test Data - " + actionName, "Actual Value", testData);
            attachments.add(actualValueAttachment);
        } else if (testData != null && !testData.isEmpty()) {
            message = message + " With the following test data [" + testData + "].";
        }

        if (queryResult != null && !queryResult.trim().equals("")) {
            attachments.add(Arrays.asList("Database Action Actual Result", "Query Result", queryResult));
        }

        if (!attachments.equals(new ArrayList())) {
            ReportManager.log(message);
        } else {
            ReportManager.log(message);
        }

        return message;
    }

    private static StringBuilder readColumnHeaders(ResultSet resultSet, boolean readColumnNames, int columnsCount) throws SQLException {
        StringBuilder str = new StringBuilder();
        if (readColumnNames) {
            for(int i = 1; i <= columnsCount; ++i) {
                str.append(resultSet.getMetaData().getColumnName(i));
                if (i != columnsCount) {
                    str.append("\t");
                }
            }

            str.append("\n");
        }

        return str;
    }

    private static StringBuilder readColumnData(ResultSet resultSet, int columnsCount, int lastRowID) throws SQLException {
        StringBuilder str = new StringBuilder();

        for(int i = 1; i <= lastRowID; ++i) {
            resultSet.absolute(i);

            for(int j = 1; j <= columnsCount; ++j) {
                str.append(resultSet.getString(j));
                if (j != columnsCount) {
                    str.append("\t");
                }
            }

            str.append("\n");
        }

        return str;
    }

    private static String getResultStringValue(ResultSet resultSet, boolean readColumnNames) {
        StringBuilder str = new StringBuilder();

        try {
            resultSet.beforeFirst();
            if (resultSet.last()) {
                int columnsCount = resultSet.getMetaData().getColumnCount();
                int lastRowID = resultSet.getRow();
                str.append(readColumnHeaders(resultSet, readColumnNames, columnsCount));
                str.append(readColumnData(resultSet, columnsCount, lastRowID));
            }
        } catch (NullPointerException | SQLException var5) {
            ReportManager.log(String.valueOf(var5));
            failAction(var5);
        }

        return str.toString().trim();
    }

    public ResultSet executeSelectQuery(String sql) {
        ResultSet resultSet = null;

        try {
            resultSet = this.createStatement(this.createConnection()).executeQuery(sql);
        } catch (NullPointerException | SQLException var4) {
            ReportManager.log(String.valueOf(var4));
            failAction(this.getReportMessage("SELECT", sql), var4);
        }

        if (resultSet != null) {

            passAction(this.getReportMessage("SELECT", sql), getResultStringValue(resultSet, true));
        } else {
            failAction("Null or no resultSet was returned from executing this query [" + sql + "]");
        }

        return resultSet;
    }

    public int executeUpdateQuery(String sql) {
        int updatedRows = 0;

        try {
            updatedRows = this.createStatement(this.createConnection()).executeUpdate(sql);
            passAction(sql);
        } catch (NullPointerException | SQLException var4) {
            ReportManager.log(String.valueOf(var4));
            failAction(this.getReportMessage("UPDATE", sql), var4);
        }

        return updatedRows;
    }

    private Connection createConnection() {
        Connection connection = null;


        try {
            connectionString = LoadProperties.userData.getProperty("DBConn");

            if (System.getProperty("databaseLoginTimeout") == null) {
                PropertyFileManager.readPropertyFiles();
            }

            DriverManager.setLoginTimeout(Integer.parseInt(System.getProperty("databaseLoginTimeout")));
            connection = DriverManager.getConnection(connectionString, this.username, this.password);

        } catch (SQLException var4) {
            ReportManager.log(String.valueOf(var4));
            failAction(connectionString, var4);
        }

        if (connection != null) {
            ReportManager.logDiscrete("Connection created successfully");
        } else {
            failAction("Failed to create a connection with this string [" + connectionString + "] due to an unhandled exception.");
        }

        return connection;
    }

    private Statement createStatement(Connection connection) {
        Statement statement = null;

        try {
            statement = connection.createStatement(1004, 1007);
            statement.setQueryTimeout(Integer.parseInt(System.getProperty("databaseQueryTimeout")));
        } catch (SQLFeatureNotSupportedException var4) {
            if (!var4.getMessage().contains("org.postgresql.jdbc4.Jdbc4Statement.setQueryTimeout")) {
                ReportManager.log(String.valueOf(var4));
                failAction(connection.toString(), var4);
            }
        } catch (SQLException var5) {
            ReportManager.log(String.valueOf(var5));
            failAction(connection.toString(), var5);
        }

        if (statement != null) {
            ReportManager.logDiscrete("Statement created successfully");
        } else {
            failAction("Failed to create a statement with this string [" + connection.toString() + "] due to an unhandled exception.");
        }

        return statement;
    }

    private String getReportMessage(String queryType, String query) {
        return "Database Type: \""  + "\"| Server: \"" +  ":"  + "\"| Name: \"" + this.connectionString + "\"| Username: \"" + this.username + "\"| Password: \"" + this.password.replaceAll(".", "*") + "\"| Query Type: \"" + queryType + "\"| Query: \"" + query + "\"";
    }
}
