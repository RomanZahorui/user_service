package utils.readers;

import java.io.IOException;

/**
 * An interface to "read" data from the type to the another one.
 *
 * @param <O> output type.
 * @param <I> input type.
 */
@FunctionalInterface
public interface BaseReader<O, I> {
    O read(I input) throws IOException;
}
