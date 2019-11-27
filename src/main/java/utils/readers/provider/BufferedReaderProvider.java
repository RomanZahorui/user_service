package utils.readers.provider;

import java.io.BufferedReader;
import utils.execptions.ReadFileException;

/**
 * Returns a buffered reader for reading a text in a specified file.
 */
public interface BufferedReaderProvider {
    /**
     * Returns a buffered reader for reading the specified file's path.
     * Any implementation of the method has to close {@link BufferedReader} after usage.
     *
     * @param path a file path
     * @return for the file.
     * @throws ReadFileException if any I / O exceptions occur during the process.
     */
    BufferedReader getBufferedReader(String path) throws ReadFileException;
}
