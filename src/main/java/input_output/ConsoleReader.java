package input_output;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class implements {@link DataReader} interface.
 */
public class ConsoleReader implements DataReader {

    private final Scanner scanner;

    /**
     * Constructor.
     *
     * @param scanner {@link Scanner} object to read input data.
     */
    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a single int value.
     *
     * @return the result {@code int} value.
     * @throws InputMismatchException if the input data can't be parse into an {@code int} value.
     */
    @Override
    public int readInt() throws InputMismatchException {
        return scanner.nextInt();
    }

    /**
     * Reads a single String line.
     *
     * @return the result {@code String} value.
     */
    @Override
    public String readLine() {
        return scanner.next();
    }
}
