package xyz.tozymc.spigot.api.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

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
   * @param <E>      Type of elements of the list
   * @return The list with specified elements
   */
  @NotNull
  @Contract("_ -> new")
  @SafeVarargs
  public static <E> ArrayList<E> newArrayList(E... elements) {
    return new ArrayList<>(Arrays.asList(elements));
  }


  /**
   * Adds elements to list and returns it.
   *
   * @param list     The original list
   * @param elements An array of element
   * @param <E>      Type of elements of the list
   * @return The list has added the specified elements
   */
  @NotNull
  @Contract("_, _ -> param1")
  @SafeVarargs
  public static <E> List<E> addAll(@NotNull List<E> list, E... elements) {
    list.addAll(Arrays.asList(elements));
    return list;
  }

  /**
   * Set all elements of the specified list, using the provided function to compute each element.
   *
   * @param list     The original list
   * @param function A function produces a new value for that value
   * @param <T>      Type of elements of the list
   * @return The list has been set
   */
  @NotNull
  @Contract("_, _ -> param1")
  public static <T> List<T> setAll(@NotNull List<T> list, Function<T, T> function) {
    ListIterator<T> iterator = list.listIterator();
    while (iterator.hasNext()) {
      iterator.set(function.apply(iterator.next()));
    }
    return list;
  }
}
