import executors.ApplicationExecutor;
import input_output.*;
import java.util.Scanner;
import model.connector.ConnectionProvider;
import model.connector.mysql.JDBCConnector;
import utils.readers.file.CSVFileReader;
import utils.readers.file.PropertyFileReader;
import utils.readers.file.PropertyReader;
import utils.readers.file.RecordsReader;
import utils.readers.script.ScriptFileReader;
import utils.readers.script.ScriptReader;

/**
 * The main to start the application execution.
 */
public class Application {

    public static void main(String[] strings) {
        // Preparing the JDBC connection provider.
        ConnectionProvider provider = new JDBCConnector();
        // Reading of user's CSV files.
        RecordsReader fileReader = new CSVFileReader();
        // Reading of a connection property file.
        PropertyReader propertyReader = new PropertyFileReader();
        // Reading of a sql script files.
        ScriptReader scriptReader = new ScriptFileReader();

        // Preparation of objects for working with console input / output.
        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new ConsoleReader(scanner);
        DataPrinter dataPrinter = new ConsolePrinter();
        InOutHandler ioHandler = new ConsoleInOutHandler(dataPrinter, dataReader);

        // Initialization and start of the program logic executor.
        ApplicationExecutor executor = new ApplicationExecutor(
            provider, fileReader, propertyReader, scriptReader, ioHandler);
        executor.execute();
    }
}
