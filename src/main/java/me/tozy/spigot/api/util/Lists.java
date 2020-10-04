package me.tozy.spigot.api.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Static utility methods pertaining to {@link List} instances
 *
 * @author TozyMC
 * @since 1.0
 */
public final class Lists {

  private Lists() {
  }

  /**
   * Creates an {@code ArrayList} instance containing the given elements.
   *
   * @param elements Array of element
   * @param <T>      Type of list
   * @return The list with specified elements
   */
  @NotNull
  @Contract("_ -> new")
  @SafeVarargs
  public static <T> ArrayList<T> newArrayList(T... elements) {
    return new ArrayList<>(Arrays.asList(elements));
  }


  /**
   * Adds elements to list and returns it.
   *
   * @param list     The original list
   * @param elements An array of element
   * @param <T>      Type of list
   * @return The list has added the specified elements
   */
  @NotNull
  @Contract("_, _ -> param1")
  @SafeVarargs
  public static <T> List<T> addAll(@NotNull List<T> list, T... elements) {
    list.addAll(Arrays.asList(elements));
    return list;
  }
}
