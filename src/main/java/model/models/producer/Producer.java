package model.models.producer;

import utils.Formatter;

/**
 * The interface designed primarily to convert a list of strings representing the internal state
 * of the resulting {@code R} type into an instance of that type. Uses an implementation of
 * {@link Formatter} interface to format the strings.
 *
 * @param <T> a type of the first argument.
 * @param <F> a type of the second argument.
 * @param <R> a return type.
 */
@FunctionalInterface
public interface Producer<T, F, R> {
    R produce(T t, F f);
}
