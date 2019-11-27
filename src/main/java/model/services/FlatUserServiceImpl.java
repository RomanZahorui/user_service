package model.services;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.entities.FlatUser;

/**
 * An implementation of {@link FlatUserService} interface.
 */
public class FlatUserServiceImpl implements FlatUserService {

    /**
     * Constants and SQL queries.
     */
    private static final String schema = "user_registry";
    private static final String table = "flat_users";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BIRTHDAY = "date_of_birth";
    private static final String CITY = "city_name";
    private static final String COUNTRY = "country_name";
    private static final String SQL_SELECT_BY_ID = "SELECT FROM " + schema + "." + table + " WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM " + schema + "." + table;
    private static final String SQL_INSERT = "INSERT INTO " + schema + "." + table
        + " (" + ID + ", " + NAME + ", " + BIRTHDAY + ", " + CITY + ", " + COUNTRY + ") VALUES (?, ?, ?, ?, ?);";
    private final Connection connection;

    /**
     * Constructor.
     *
     * @param connection a {@link Connection} with the database.
     */
    public FlatUserServiceImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(FlatUser user) throws SQLException {
        save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertAll(List<FlatUser> users) throws SQLException {
        for (FlatUser u : users) {
            save(u);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FlatUser> selectAll() throws SQLException {
        List<FlatUser> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                LocalDate birth = resultSet.getDate(3).toLocalDate();
                String cityName = resultSet.getString(4);
                String countryName = resultSet.getString(5);
                users.add(new FlatUser(id, name, birth, cityName, countryName));
            }
        }
        return users;
    }

    /**
     * Creates an instance of {@link PreparedStatement} to execute an insertion of {@code user}.
     *
     * @param user to save in the DB.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection.
     */
    private void save(FlatUser user) throws SQLException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT)) {
            stm.setLong(1, user.getId());
            stm.setString(2, user.getName());
            stm.setDate(3, Date.valueOf(user.getBirthday()));
            stm.setString(4, user.getCityName());
            stm.setString(5, user.getCountryName());
            stm.executeUpdate();
        }
    }
}
