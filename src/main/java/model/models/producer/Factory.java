package model.models.producer;

import java.util.List;
import utils.Formatter;

/**
 * The interface extends {@link Producer} interface. Produces an instance of the {@code T} base on data contained in
 * the {@code List<String>} parameter and {@link Formatter} to format these strings.
 *
 * @param <T> a result type.
 */
@FunctionalInterface
public interface Factory<T> extends Producer<List<String>, Formatter, T> {
}
