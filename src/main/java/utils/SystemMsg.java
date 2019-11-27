package utils;

/**
 * System messages.
 */
public interface SystemMsg {
    String INSERT_COUNTRIES_FILE_PATH = "Pleas, insert a path for \"Countries.csv\" file : ";
    String INSERT_CITIES_FILE_PATH = "Pleas, insert a path for \"Cities.csv\" file : ";
    String INSERT_USERS_FILE_PATH = "Pleas, insert a path for \"Users.csv\" file : ";

    String SCRIPT_SCHEMA = "create_schema.sql";
    String SCRIPT_TABLE = "create_table.sql";
    String PROPERTY_FILE = "mysql.db.properties";

    String FORMAT_REGEX = "[^a-zA-Z0-9|(\\s-)]";

    String DATA_LOADING = "Data loading...";
    String MODELS_CREATION = "Models creation...";
    String ESTABLISH_CONNECTION = "Establish db connection...";
    String SCRIPT_EXECUTION = "Create the schema and the table...";
    String DATA_SAVING = "Save the users to the DB...";
    String READ_COUNTRIES_FILE = "Read file with countries data...";
    String READ_CITIES_FILE = "Read file with cities data...";
    String READ_USERS_FILE = "Read file with users data...";
    String SAVED_USER_MSG = "All users has been inserted into the registry.";
    String STORED_USERS_MSG = "User registry has : ";

    String ERROR_FILE_READING = "Can't read files from resources directory!\n";
    String ERROR_DATA_PARSING = "Can't parse the data!\n";
    String ERROR_WHILE_CONNECTION = "Can't establish a connection!\n";
    String ERROR_EXECUTING_SQL = "Can't execute sql statement!\n";
    String ERROR_WHILE_SAVING = "Can't save the users to the DB!\n";
    String ERROR_WHILE_SELECTING = "Can't select users!\n";
}
