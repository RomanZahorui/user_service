package utils.readers.provider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import utils.execptions.ReadFileException;

/**
 * The class provides opening of a {@link BufferedReader} for a resource file by
 * using of the {@code getClass().getClassLoader().getResourceAsStream(path)} structure.
 */
public class ResourceReaderProvider implements BufferedReaderProvider {

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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        if (null == inputStream) {
            throw new ReadFileException("Can't read the file cause the input stream for "
                + path + " is null!");
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
