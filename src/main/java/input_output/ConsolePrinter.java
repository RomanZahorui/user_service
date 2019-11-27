package input_output;

import org.apache.log4j.Logger;

/**
 * The class implements {@link DataPrinter} interface.
 */
public class ConsolePrinter implements DataPrinter {
    private final Logger logger = Logger.getLogger(ConsolePrinter.class.getSimpleName());

    /**
     * Prints a message and then terminate the line.
     *
     * @param msg string message.
     */
    @Override
    public void print(String msg) {
        logger.trace(msg);
    }

    /**
     * Prints a new line and the message and then terminate the line.
     *
     * @param msg string message.
     */
    @Override
    public void println(String msg) {
        print(msg);
    }

    /**
     * Prints a new line and the error message and then terminate the line.
     *
     * @param errorMsg string error message.
     */
    @Override
    public void printErr(String errorMsg) {
        logger.error(errorMsg);
    }
}
