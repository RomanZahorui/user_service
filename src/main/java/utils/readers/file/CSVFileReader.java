package utils.readers.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.readers.provider.BufferedReaderProvider;

/**
 * An implementation of the {@link RecordsReader}.
 */
public class CSVFileReader implements RecordsReader {

    /**
     * Reads all lines presented in the file and maps them into the list.
     * Uses {@link BufferedReaderProvider} to get {@link BufferedReader} for file path.
     *
     * @param path a name of a resource file or path of the file.
     * @return a list of all lines presented in the file.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public List<String> read(BufferedReaderProvider readerProvider, String path) throws IOException {
        final List<String> records = new ArrayList<>();
        try (BufferedReader reader = readerProvider.getBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        }
        return records;
    }
}
