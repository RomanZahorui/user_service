package utils;

import java.util.function.UnaryOperator;

/**
 * The interface for formatting strings.
 */
@FunctionalInterface
public interface Formatter extends UnaryOperator<String> {
}
