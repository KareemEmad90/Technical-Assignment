package data;

import com.shaft.db.DatabaseActions;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnections {
    DatabaseActions databaseActions ;
    /**
     * This constructor is used for initializing the database variables that are
     * needed to create new connections and perform queries
     *
     * @param databaseType database type that you want to connect with:
     *                     DatabaseType.MY_SQL ,SQL_SERVER,POSTGRE_SQL.
     * @param ip           IP address that has database installation that we need to
     *                     connect to (e.g. 72.55.136.25)
     * @param port         port of database installation on the server (e.g. 3306)
     * @param name         database name that you need to connect to
     * @param username     database username
     * @param password     password of database user
     */
    private String databaseType , ip , port, dbname , username , password ;

    public void setConnection(){

        username = LoadProperties.userData.getProperty("DBUserName");
        password = LoadProperties.userData.getProperty("DBPassword");
        dbname = LoadProperties.userData.getProperty("dbname");
        ip = LoadProperties.userData.getProperty("ip");
        port = LoadProperties.userData.getProperty("port");
        databaseActions = new DatabaseActions(DatabaseActions.DatabaseType.ORACLE,ip,port,dbname,username,password);
    }


}
