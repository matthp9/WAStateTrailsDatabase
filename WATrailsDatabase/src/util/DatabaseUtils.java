package WATrailsDatabase.src.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for connection to the database;
 */
public class DatabaseUtils {

    private final String userName;
    private final String password;
    private final String serverName;

    public DatabaseUtils(String userName, String password, String serverName) {
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
    }

    public Connection createConnection() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        Connection conn = null;
        try {
             conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
                    + serverName + "/", connectionProps);
        } catch (SQLException s) {
            System.out.println("Cannot connect to database.");
            System.out.println("SQL Error: " + s.getSQLState());
            System.out.println(s.getMessage());
        }


        return conn;
    }
}
