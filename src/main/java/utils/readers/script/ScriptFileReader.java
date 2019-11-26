package utils.readers.script;

import java.io.BufferedReader;
import java.io.IOException;
import utils.readers.provider.BufferedReaderProvider;

/**
 * An implementation of the {@link ScriptReader}.
 */
public class ScriptFileReader implements ScriptReader {

    /**
     * Reads all lines presented in the file and maps them into the single String object.
     *
     * @param path a name of a resource file or path of the file.
     * @return a list of all lines presented in the file.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String read(BufferedReaderProvider readerProvider, String path) throws IOException {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader br = readerProvider.getBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
