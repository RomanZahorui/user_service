package utils.readers.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link FileReader}. The class provides opening and
 * closing of an {@code FileInputStream} for reading all lines in a file by it's path.
 */
public class UriFileReader implements FileReader {

    /**
     * Reads all lines presented in the file and maps them into the list.
     *
     * @param filePath an absolute path of the file.
     * @return a list of all lines presented in the file.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public List<String> read(String filePath) throws IOException {
        final List<String> records = new ArrayList<>();
        final File file = new File(filePath);
        try (InputStream stream = new FileInputStream(file)) {
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
