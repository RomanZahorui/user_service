package utils.readers.script;

import utils.readers.BaseReader;

/**
 * An extension of {@link BaseReader} with {@link String}
 * parameter to read all lines from a specified file into a single String.
 */
@FunctionalInterface
public interface ScriptReader extends BaseReader<String> {
}
