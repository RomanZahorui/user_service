package input_output;

/**
 * The interface provides methods for reading of user's messages.
 */
public interface DataReader {

    /**
     * Read an int value.
     *
     * @return {@code int} value.
     */
    int readInt();

    /**
     * Read a line value.
     *
     * @return {@code String} value.
     */
    String readLine();
}
