import executors.RegistryExecutor;
import input_output.*;
import java.util.Scanner;
import model.connector.ConnectionProvider;
import model.connector.mysql.JDBCConnector;
import utils.readers.file.FileReader;
import utils.readers.file.UriFileReader;

/**
 * The main to start the application execution.
 */
public class Application {

    public static void main(String[] strings) {
        // Preparing the JDBC connection provider.
        ConnectionProvider provider = new JDBCConnector();
        // A file reade instance to read user's CSV files.
        FileReader fileReader = new UriFileReader();

        // Preparation of objects for working with console input / output.
        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new ConsoleReader(scanner);
        DataPrinter dataPrinter = new ConsolePrinter();
        InOutHandler ioHandler = new ConsoleInOutHandler(dataPrinter, dataReader);

        // Initialization and start of the program logic executor.
        RegistryExecutor executor = new RegistryExecutor(provider, fileReader, ioHandler);
        executor.execute();
    }
}
