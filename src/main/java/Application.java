import executors.RegistryExecutor;
import input_output.*;
import java.util.Scanner;
import model.connector.ConnectionProvider;
import model.connector.mysql.MySqlConnector;
import utils.readers.file.FileReader;
import utils.readers.file.UriFileReader;

public class Application {

    public static void main(String[] strings) {
        ConnectionProvider provider = new MySqlConnector();

        FileReader fileReader = new UriFileReader();

        Scanner scanner = new Scanner(System.in);
        DataReader dataReader = new ConsoleReader(scanner);
        DataPrinter dataPrinter = new ConsolePrinter();
        InOutHandler ioHandler = new ConsoleDataHandler(dataPrinter, dataReader);

        RegistryExecutor executor = new RegistryExecutor(provider, fileReader, ioHandler);
        executor.execute();
    }
}
