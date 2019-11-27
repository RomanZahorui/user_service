package utils.readers.provider;

import java.io.*;
import utils.execptions.ReadFileException;

/**
 * The class provides opening of a {@link BufferedReader} for a system file by
 * creating of the {@link File} object for the specified file path.
 */
public class SystemFileReaderProvider implements BufferedReaderProvider {

    /**
     * Returns a buffered reader for reading the specified file's path.
     * A client's code has to close {@link BufferedReader} after usage.
     *
     * @param path a file path
     * @return for the file.
     * @throws ReadFileException if the input stream is null.
     */
    @Override
    public BufferedReader getBufferedReader(String path) throws ReadFileException {
        final File file = new File(path);
        try {
            InputStream inputStream = new FileInputStream(file);
            return new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            throw new ReadFileException("Can't read the file " + path, e);
        }
    }
}
