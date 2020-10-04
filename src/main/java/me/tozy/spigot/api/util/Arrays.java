package me.tozy.spigot.api.util;

import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

/**
 * Static utility methods pertaining to array.
 *
 * @author TozyMC
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

}
