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
     * Constants and SQL statements.
     */
    private static final String schema = "user_registry";
    private static final String table = "flat_users";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BIRTHDAY = "date_of_birth";
    private static final String CITY = "city_name";
    private static final String COUNTRY = "country_name";

    private static final int ID_IDX = 1;
    private static final int NAME_IDX = 2;
    private static final int BIRTHDAY_IDX = 3;
    private static final int CITY_IDX = 4;
    private static final int COUNTRY_IDX = 5;

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
        for (FlatUser user : users) {
            save(user);
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
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                LocalDate birth = resultSet.getDate(BIRTHDAY).toLocalDate();
                String cityName = resultSet.getString(CITY);
                String countryName = resultSet.getString(COUNTRY);
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
            stm.setLong(ID_IDX, user.getId());
            stm.setString(NAME_IDX, user.getName());
            stm.setDate(BIRTHDAY_IDX, Date.valueOf(user.getBirthday()));
            stm.setString(CITY_IDX, user.getCityName());
            stm.setString(COUNTRY_IDX, user.getCountryName());
            stm.executeUpdate();
        }
    }
}
