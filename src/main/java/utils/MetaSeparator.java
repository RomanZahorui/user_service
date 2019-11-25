package utils;

import java.util.List;

/**
 * The interface provides a method for splitting a {@code String} containing object's state
 * into separate lines describing only one value.
 */
@FunctionalInterface
public interface MetaSeparator {
    /**
     * Separates an incoming string into the list of separated values.
     *
     * @param metadata a {@code String} containing object's state
     * @return a {@link List<String>} with object's state values.
     */
    List<String> separate(String metadata);
}
