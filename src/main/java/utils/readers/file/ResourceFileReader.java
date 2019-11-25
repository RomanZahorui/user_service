package utils.readers.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link FileReader}. The class provides opening and
 * closing of an {@code InputStream} for reading all lines in a file.
 */
public class ResourceFileReader implements FileReader {

    /**
     * Reads all lines presented in the file and maps them into the list.
     *
     * @param resourceName a name of a file in {@code /resources/} directory.
     * @return a list of all lines presented in the file.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public List<String> read(String resourceName) throws IOException {
        final List<String> records = new ArrayList<>();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (null == stream) {
                throw new IOException("Can't read the file : " + resourceName);
            }

            Reader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                // add to the result list
                records.add(line);
                // read next line
                line = br.readLine();
            }
        }

        return records;
    }
}
