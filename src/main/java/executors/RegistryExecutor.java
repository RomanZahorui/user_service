package executors;

import input_output.InOutHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.connector.ConnectionProvider;
import model.entities.FlatUser;
import model.entities.mapper.FlatUserMapper;
import model.entities.mapper.UserDataMapper;
import model.models.City;
import model.models.Country;
import model.models.User;
import model.models.producer.impl.CityFactory;
import model.models.producer.impl.CountryFactory;
import model.models.producer.impl.UserFactory;
import model.services.FlatUserService;
import model.services.FlatUserServiceImpl;
import utils.Formatter;
import utils.MetaSeparator;
import utils.readers.file.FileReader;
import utils.readers.property.PropertyReader;
import utils.readers.script.ScriptLoader;

/**
 * The class implement {@link Executor} interface.
 * Initiates a sequence of actions representing the application logic.
 */
public class RegistryExecutor implements Executor {

    private ConnectionProvider provider;
    private InOutHandler ioHandler;
    private FileReader fileReader;

    private String countriesFileUri;
    private String citiesFileUri;
    private String usersFileUri;

    private List<String> countryRecords;
    private List<String> cityRecords;
    private List<String> userRecords;

    private List<Country> countries;
    private List<City> cities;
    private List<User> users;

    public RegistryExecutor(ConnectionProvider provider, FileReader fileReader, InOutHandler ioHandler) {
        this.provider = provider;
        this.fileReader = fileReader;
        this.ioHandler = ioHandler;
    }

    public void execute() {
        ioHandler.print("Pleas, insert a path for \"Countries.csv\" file : ");
        countriesFileUri = ioHandler.readLine();

        ioHandler.print("Pleas, insert a path for \"Cities.csv\" file : ");
        citiesFileUri = ioHandler.readLine();

        ioHandler.print("Pleas, insert a path for \"Users.csv\" file : ");
        usersFileUri = ioHandler.readLine();

        ioHandler.println("Data loading ...");
        if (!loadAllData(fileReader)) {
            return;
        }

        ioHandler.println("Models creation ...");
        boolean isParsed = parseData(s -> Arrays.asList(s.split(",")),
            s -> s.replaceAll("[^a-zA-Z0-9|-]", ""),
            ioHandler);
        if (!isParsed) {
            return;
        }

        ioHandler.println("Establish db connection ...");
        Connection connection = establishConnection(provider, ioHandler);
        if (connection == null) {
            return;
        }

        ioHandler.println("Create the schema and the table ...");
        boolean isExecuted = executeInitialScripts(connection, ioHandler);
        if (!isExecuted) {
            return;
        }

        ioHandler.println("Save the users to the DB...");
        FlatUserService service = new FlatUserServiceImpl(connection);
        UserDataMapper mapper = new FlatUserMapper();
        boolean isSaved = saveUsers(service, mapper, ioHandler);
        if (isSaved) {
            ioHandler.println("All users has been inserted into the registry.");
        } else {
            return;
        }

        List<FlatUser> savedUsers = selectAllUsers(service, ioHandler);
        if (null != savedUsers && !savedUsers.isEmpty()) {
            ioHandler.println("User registry has : ");
            savedUsers.forEach(u -> ioHandler.println(u.toString()));
        }
    }

    private boolean loadAllData(FileReader reader) {
        try {
            ioHandler.println("Read file with countries data...");
            countryRecords = loadData(reader, countriesFileUri);

            ioHandler.println("Read file with cities data...");
            cityRecords = loadData(reader, citiesFileUri);

            ioHandler.println("Read file with users data...");
            userRecords = loadData(reader, usersFileUri);
        } catch (IOException e) {
            ioHandler.print("Can't read files from resources directory !\n" + e.getMessage());
            return false;
        }
        return true;
    }

    private List<String> loadData(FileReader reader, String fileUri) throws IOException {
        return reader.read(fileUri);
    }

    private boolean parseData(MetaSeparator separator, Formatter formatter, InOutHandler ioHandler) {
        try {
            countries = countryRecords.stream()
                .map(s -> new CountryFactory().produce(separator.separate(s), formatter))
                .collect(Collectors.toList());

            cities = cityRecords.stream()
                .map(s -> new CityFactory().produce(separator.separate(s), formatter))
                .collect(Collectors.toList());

            users = userRecords.stream()
                .map(s -> new UserFactory().produce(separator.separate(s), formatter))
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            ioHandler.printErr("Can't parse the date cause : " + e.getMessage());
            return false;
        }
        return true;
    }

    private Connection establishConnection(ConnectionProvider provider, InOutHandler ioHandler) {
        Connection connection = null;
        try {
            connection = provider.getConnection(new PropertyReader().read("mysql.db.properties"));
        } catch (SQLException | IOException e) {
            ioHandler.printErr("Can't establish a connection cause : " + e.getMessage());
        }
        return connection;
    }

    private boolean executeInitialScripts(Connection connection, InOutHandler ioHandler) {

        try (Statement st = connection.createStatement()) {
            ScriptLoader loader = new ScriptLoader();

            try {
                String schema = loader.read("create_schema.sql");
                st.executeUpdate(schema);
            } catch (SQLException | IOException e) {
                ioHandler.printErr("Can't execute sql statement!\n" + e.getMessage());
            }

            try {
                String table = loader.read("create_table.sql");
                st.executeUpdate(table);
            } catch (SQLException | IOException e) {
                ioHandler.printErr("Can't execute sql statement!\n" + e.getMessage());
            }
        } catch (SQLException e) {
            ioHandler.printErr("Can't execute sql statement!\n" + e.getMessage());
            return false;
        }

        return true;
    }

    private boolean saveUsers(FlatUserService service, UserDataMapper mapper, InOutHandler ioHandler) {
        try {
            return service.insertAll(mapper.mapToList(users, cities, countries));
        } catch (SQLException e) {
            ioHandler.printErr("Can't save the users to the DB cause : " + e.getMessage());
            return false;
        }
    }

    private List<FlatUser> selectAllUsers(FlatUserService service, InOutHandler ioHandler) {
        try {
            return service.selectAll();
        } catch (SQLException e) {
            ioHandler.printErr("Can't select users cause : " + e.getMessage());
            return null;
        }
    }
}
