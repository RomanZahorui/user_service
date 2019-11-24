package connector.mysql;

import connector.ConnectionProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The class implements {@link ConnectionProvider}
 * {@inheritDoc}
 */
public class MySqlConnector implements ConnectionProvider {

    /**
     * @param properties an instance of {@link java.util.Properties} with :
     *                   mysql.jdbc.driver, mysql.jdbc.url, mysql.jdbc.username, mysql.jdbc.password.
     * @return an instance of the {@link java.sql.Connection}
     * @throws SQLException if a database access error occurs or the url is {@code null}
     */
    public Connection getConnection(Properties properties) throws SQLException {
        return DriverManager.getConnection(
            properties.getProperty("mysql.jdbc.url"),
            properties.getProperty("mysql.jdbc.username"),
            properties.getProperty("mysql.jdbc.password"));
    }
}
