package model.services;

import model.entities.FlatUser;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface to store and retrieve {@link FlatUser} objects.
 */
public interface FlatUserService {

    /**
     * Inserts an {@link FlatUser} to a database.
     *
     * @param user object.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    void insert(FlatUser user) throws SQLException;

    /**
     * Inserts contained in the list of {@link FlatUser} objects to a database.
     *
     * @param users objects.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    boolean insertAll(List<FlatUser> users) throws SQLException;

    /**
     * Retrieves and returns all database {@link FlatUser} rows.
     *
     * @return
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    List<FlatUser> selectAll() throws SQLException;
}
