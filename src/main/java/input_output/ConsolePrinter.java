package input_output;

/**
 * The class implements {@link DataPrinter} interface.
 */
public class ConsolePrinter implements DataPrinter {

    /**
     * Prints a message and then terminate the line.
     *
     * @param msg string message.
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints a new line and the message and then terminate the line.
     *
     * @param msg string message.
     */
    @Override
    public void println(String msg) {
        printNewLine();
        print(msg);
    }

    /**
     * Prints a new line and the error message and then terminate the line.
     *
     * @param errorMsg string error message.
     */
    @Override
    public void printErr(String errorMsg) {
        printNewLine();
        print("Error : " + errorMsg);
    }

    /**
     * Prints a new line.
     */
    private void printNewLine() {
        System.out.println();
    }
}
