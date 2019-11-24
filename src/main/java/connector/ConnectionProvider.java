package connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides the interface to a data source connection.
 */
@FunctionalInterface
public interface ConnectionProvider {
    /**
     * Provides a connection to a data source.
     * @param properties an instance of {@link java.util.Properties} with a driver, url, user, password information.
     * @return an instance of the {@link java.sql.Connection}
     * @throws SQLException if a database access error occurs or the url is {@code null}
     */
    Connection getConnection(Properties properties) throws SQLException;
}
