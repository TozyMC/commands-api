package xyz.tozymc.spigot.api.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;
import java.util.function.IntFunction;

/**
 * Static utility methods pertaining to array.
 *
 * @author TozyMC
 * @since 1.0
 */
public final class Arrays {

  private Arrays() {
  }

  /**
   * Joins string array with {@link StringJoiner}.
   *
   * @param joiner  A string joiner (separator)
   * @param strings An array of string
   * @return The string has joined with joiner
   */
  @NotNull
  public static String joinToString(@NotNull StringJoiner joiner, @NotNull String... strings) {
    for (String string : strings) {
      joiner.add(string);
    }
    joiner.setEmptyValue("");
    return joiner.toString();
  }

  /**
   * Checks string is empty or not.
   *
   * @param strings An array of string
   * @return true is array is empty, else false
   */
  @Contract("null -> true")
  public static boolean isEmpty(String[] strings) {
    if (strings == null) {
      return true;
    }
    if (strings.length == 0) {
      return true;
    }
    return java.util.Arrays.stream(strings).allMatch(s -> s.equals(""));
  }

  /**
   * Set all elements of the specified array, using the provided generator function to compute each
   * element.
   *
   * <p>If the generator function throws an exception, it is relayed to
   * the caller and the array is left in an indeterminate state.
   *
   * @param <T>       Type of elements of the array
   * @param array     Array to be initialized
   * @param generator A function accepting an index and producing the desired value for that
   *                  position
   * @see java.util.Arrays#setAll(Object[], IntFunction)
   * @since 1.0
   */
  public static <T> void setAll(@NotNull T[] array, @NotNull IntFunction<? extends T> generator) {
    java.util.Arrays.setAll(array, generator);
  }
}
