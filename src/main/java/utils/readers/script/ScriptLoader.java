package utils.readers.script;

import java.io.*;
import utils.readers.BaseReader;

/**
 * An implementation of the {@link BaseReader<String, String>} interface.
 * The class created for reading sql scripts from resource files and mapping them into a {@code String}
 */
public class ScriptLoader implements BaseReader<String, String> {

    /**
     * @param scriptName the name of an .sql file.
     * @return a string representation of a stored sql query.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String read(String scriptName) throws IOException {
        final StringBuilder sb = new StringBuilder();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(scriptName)) {
            if (null == stream) {
                throw new IOException("Can't read the file : " + scriptName);
            }

            Reader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        }

        return sb.toString();
    }
}
