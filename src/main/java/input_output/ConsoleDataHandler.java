package input_output;

/**
 * The class implements {@link InOutHandler}.
 * {@inheritDoc}
 */
public class ConsoleDataHandler implements InOutHandler {

    private DataPrinter printer;
    private DataReader reader;

    /**
     * Constructor.
     *
     * @param printer a {@link DataPrinter} implementation.
     * @param reader  a {@link DataReader} implementation.
     */
    public ConsoleDataHandler(DataPrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int readInt() {
        return reader.readInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String readLine() {
        return reader.readLine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print(String msg) {
        printer.print(msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void println(String msg) {
        printer.println(msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printErr(String errorMsg) {
        printer.printErr(errorMsg);
    }
}
