package utils.readers;

import java.io.IOException;
import utils.readers.provider.BufferedReaderProvider;

/**
 * The interface is designed for any text file reading operation.
 * Requires an implementation of  {@link BufferedReaderProvider}.
 *
 * @param <O> output type.
 * @see BufferedReaderProvider
 */
@FunctionalInterface
public interface BaseReader<O> {
    /**
     * @param readerProvider provides an open {@link java.io.BufferedReader}
     *                       that should be closed after usage.
     * @param path           of the file.
     * @return result of the reading.
     * @throws IOException if an I/O exception has occurred.
     */
    O read(BufferedReaderProvider readerProvider, String path) throws IOException;
}
