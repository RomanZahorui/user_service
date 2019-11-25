package input_output;

/**
 * The interface provides methods for printing of messages.
 */
public interface DataPrinter {

    /**
     * Just prints the message.
     *
     * @param msg string message.
     */
    void print(String msg);

    /**
     * Prints the message and a single newline character.
     *
     * @param msg string message.
     */
    void println(String msg);

    /**
     * Prints the error message.
     *
     * @param errorMsg string error message.
     */
    void printErr(String errorMsg);

}
