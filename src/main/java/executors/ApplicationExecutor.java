package executors;

import input_output.InOutHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.BaseModel;
import model.connector.ConnectionProvider;
import model.entities.FlatUser;
import model.entities.mapper.FlatUserMapper;
import model.entities.mapper.UserDataMapper;
import model.City;
import model.Country;
import model.User;
import model.producer.impl.CityFactory;
import model.producer.impl.CountryFactory;
import model.producer.impl.UserFactory;
import model.services.FlatUserService;
import model.services.FlatUserServiceImpl;
import utils.Formatter;
import utils.MetaSeparator;
import utils.SystemMsg;
import utils.execptions.NotValidDataException;
import utils.parsers.DataParser;
import utils.parsers.StringDataParser;
import utils.readers.file.PropertyReader;
import utils.readers.file.RecordsReader;
import utils.readers.provider.SystemFileReaderProvider;
import utils.readers.provider.ResourceReaderProvider;
import utils.readers.provider.BufferedReaderProvider;
import utils.readers.script.ScriptReader;

/**
 * The class implement {@link Executor} interface.
 * Initiates a sequence of actions representing the application logic.
 */
public class ApplicationExecutor implements Executor {

    private ConnectionProvider provider;
    private InOutHandler ioHandler;
    private RecordsReader fileReader;
    private PropertyReader propertyReader;
    private ScriptReader scriptReader;

    private String countriesFileUri;
    private String citiesFileUri;
    private String usersFileUri;

    private List<String> countryRecords;
    private List<String> cityRecords;
    private List<String> userRecords;

    private List<Country> countries;
    private List<City> cities;
    private List<User> users;

    /**
     * Constructor.
     *
     * @param provider   provides a DB connection.
     * @param recordsReader for a CSV file reading.
     * @param ioHandler  for app input and output process.
     */
    public ApplicationExecutor(ConnectionProvider provider,
                               RecordsReader recordsReader,
                               PropertyReader propertyReader,
                               ScriptReader scriptReader,
                               InOutHandler ioHandler) {
        this.provider = provider;
        this.fileReader = recordsReader;
        this.propertyReader = propertyReader;
        this.scriptReader = scriptReader;
        this.ioHandler = ioHandler;
    }

    /**
     * Performs a sequence of operations to execute the application logic.
     */
    public void execute() {
        ioHandler.print(SystemMsg.INSERT_COUNTRIES_FILE_PATH);
        countriesFileUri = ioHandler.readLine();

        ioHandler.print(SystemMsg.INSERT_CITIES_FILE_PATH);
        citiesFileUri = ioHandler.readLine();

        ioHandler.print(SystemMsg.INSERT_USERS_FILE_PATH);
        usersFileUri = ioHandler.readLine();

        ioHandler.println(SystemMsg.DATA_LOADING);
        if (!loadAllData(fileReader)) {
            return;
        }

        ioHandler.println(SystemMsg.MODELS_CREATION);
        boolean isParsed = parseData(s -> Arrays.asList(s.split(",")), new StringDataParser(),
            s -> s.replaceAll("[^a-zA-Z0-9|(\\s-)]", ""),
            ioHandler);
        if (!isParsed) {
            return;
        }

        ioHandler.println(SystemMsg.ESTABLISH_CONNECTION);
        Connection connection = establishConnection(provider, propertyReader, ioHandler);
        if (connection == null) {
            return;
        }

        ioHandler.println(SystemMsg.SCRIPT_EXECUTION);
        boolean isExecuted = executeInitialScripts(connection, scriptReader, ioHandler);
        if (!isExecuted) {
            return;
        }

        ioHandler.println(SystemMsg.DATA_SAVING);
        FlatUserService service = new FlatUserServiceImpl(connection);
        UserDataMapper mapper = new FlatUserMapper();
        boolean isSaved = saveUsers(service, mapper, ioHandler);
        if (!isSaved) {
            return;
        }
        ioHandler.println(SystemMsg.SAVED_USER_MSG);

        List<FlatUser> savedUsers = selectAllUsers(service, ioHandler);
        if (null != savedUsers && !savedUsers.isEmpty()) {
            ioHandler.println(SystemMsg.STORED_USERS_MSG);
            savedUsers.forEach(u -> ioHandler.println(u.toString()));
        }
    }

    /**
     * Prints an information about loading and delegates the reading operation
     * to the {@link #loadData(RecordsReader, String)} method.
     *
     * @param reader to read the specified files data.
     * @return true if the reading operation was successful.
     */
    private boolean loadAllData(RecordsReader reader) {
        try {
            ioHandler.println(SystemMsg.READ_COUNTRIES_FILE);
            countryRecords = loadData(reader, countriesFileUri);

            ioHandler.println(SystemMsg.READ_CITIES_FILE);
            cityRecords = loadData(reader, citiesFileUri);

            ioHandler.println(SystemMsg.READ_USERS_FILE);
            userRecords = loadData(reader, usersFileUri);
        } catch (IOException e) {
            ioHandler.print(SystemMsg.ERROR_FILE_READING + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Loads data from the specified files into {@code List<Strings>} represented records
     * for {@link Country},{@link City} and {@link User} objects.
     *
     * @param reader  to read the specified file data.
     * @param fileUri the file's path.
     * @return a list of strings represented records in the file.
     * @throws IOException if an error occurred when reading from the file.
     */
    private List<String> loadData(RecordsReader reader, String fileUri) throws IOException {
        return reader.read(new SystemFileReaderProvider(), fileUri);
    }

    /**
     * Tries to parse data from lists of models records into related
     * lists of {@link BaseModel}'s.
     *
     * @param separator to split record into string representations of a model parameters.
     * @param formatter to format strings of the model parameters.
     * @param ioHandler printing of an error information.
     * @return true if the parsing process was successful.
     */
    private boolean parseData(MetaSeparator separator, DataParser<String> parser,
                              Formatter formatter, InOutHandler ioHandler) {
        try {
            countries = countryRecords.stream()
                .map(s -> new CountryFactory(parser).produce(separator.separate(s), formatter))
                .collect(Collectors.toList());

            cities = cityRecords.stream()
                .map(s -> new CityFactory(parser).produce(separator.separate(s), formatter))
                .collect(Collectors.toList());

            users = userRecords.stream()
                .map(s -> new UserFactory(parser).produce(separator.separate(s), formatter))
                .collect(Collectors.toList());
        } catch (NotValidDataException e) {
            ioHandler.printErr(SystemMsg.ERROR_DATA_PARSING + e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * Tries to establish a connection to a DB by {@link ConnectionProvider}.
     *
     * @param provider  for a DB connection.
     * @param reader    a property file reader.
     * @param ioHandler printing of an error information.
     * @return an established DB connection.
     */
    private Connection establishConnection(ConnectionProvider provider, PropertyReader reader, InOutHandler ioHandler) {
        Connection connection = null;
        BufferedReaderProvider readerProvider = new ResourceReaderProvider();
        try {
            connection = provider.getConnection(reader.read(readerProvider, SystemMsg.PROPERTY_FILE));
        } catch (SQLException | IOException e) {
            ioHandler.printErr(SystemMsg.ERROR_WHILE_CONNECTION + e.getMessage());
        }
        return connection;
    }

    /**
     * Tries to execute some sql scripts for preparing the DB.
     *
     * @param connection {@link Connection} to a database.
     * @param ioHandler  printing of an error information.
     * @return true if the initial scripts was executed successful.
     */
    private boolean executeInitialScripts(Connection connection, ScriptReader scriptReader, InOutHandler ioHandler) {

        try (Statement st = connection.createStatement()) {
            BufferedReaderProvider readerProvider = new ResourceReaderProvider();

            try {
                String schema = scriptReader.read(readerProvider, SystemMsg.SCRIPT_SCHEMA);
                st.executeUpdate(schema);
            } catch (SQLException | IOException e) {
                ioHandler.printErr(SystemMsg.ERROR_EXECUTING_SQL + e.getMessage());
            }

            try {
                String table = scriptReader.read(readerProvider, SystemMsg.SCRIPT_TABLE);
                st.executeUpdate(table);
            } catch (SQLException | IOException e) {
                ioHandler.printErr(SystemMsg.ERROR_EXECUTING_SQL + e.getMessage());
            }
        } catch (SQLException e) {
            ioHandler.printErr(SystemMsg.ERROR_EXECUTING_SQL + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Tries to map collected {@code List<User>}, {@code List<City>} and {@code List<Country>} data
     * into a list of {@link FlatUser} objects and save them by {@link FlatUserService}.
     *
     * @param service   for saving the {@link FlatUser} objects into the DB.
     * @param mapper    maps all collected data into the {@link FlatUser} objects.
     * @param ioHandler printing of an error information.
     * @return true if the initial scripts was executed successful.
     */
    private boolean saveUsers(FlatUserService service, UserDataMapper mapper, InOutHandler ioHandler) {
        try {
            return service.insertAll(mapper.mapToList(users, cities, countries));
        } catch (SQLException e) {
            ioHandler.printErr(SystemMsg.ERROR_WHILE_SAVING + e.getMessage());
            return false;
        }
    }

    /**
     * Performs selection of all stored {@link FlatUser} objects in the DB.
     *
     * @param service   for retrieving the {@link FlatUser} objects from the DB.
     * @param ioHandler printing of an error information.
     * @return list of stored {@link FlatUser}s.
     */
    private List<FlatUser> selectAllUsers(FlatUserService service, InOutHandler ioHandler) {
        try {
            return service.selectAll();
        } catch (SQLException e) {
            ioHandler.printErr(SystemMsg.ERROR_WHILE_SELECTING + e.getMessage());
            return null;
        }
    }
}
