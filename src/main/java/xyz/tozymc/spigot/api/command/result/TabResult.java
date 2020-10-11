package xyz.tozymc.spigot.api.command.result;

import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the result will return when tab complete, contains search token and an iterable
 * collection of completer.
 *
 * @author TozyMC
 * @since 1.0
 */
public class TabResult {

  /**
   * An empty tab result, with no search token and an empty iterable collection.
   *
   * @see #empty()
   */
  public static final TabResult EMPTY_RESULT = new TabResult("", new ArrayList<>());

  private final String token;
  private final Iterable<String> completer;

  private TabResult(@NotNull String token, @NotNull Iterable<String> completer) {
    this.token = token;
    this.completer = completer;
  }

  /**
   * Creates new tab result with iterable collection of completer
   *
   * <p>The {@code token} usually the final parameter passed.
   *
   * @param token     String to search for
   * @param completer An iterable collection of completer to filter
   * @return The result of tab
   */
  @NotNull
  @Contract(value = "_, _ -> new", pure = true)
  public static TabResult of(@NotNull String token, @NotNull Iterable<String> completer) {
    return new TabResult(token, completer);
  }

  /**
   * Creates new tab result with array of completer
   *
   * <p>The {@code token} usually the final parameter passed.
   *
   * @param token     String to search for
   * @param completer An array of completer to filter
   * @return The result of tab
   */
  @NotNull
  @Contract(value = "_, _ -> new", pure = true)
  public static TabResult of(@NotNull String token, @NotNull String... completer) {
    return of(token, Lists.newArrayList(completer));
  }

  /**
   * Returns An empty tab result, with no search token and an empty iterable collection.
   *
   * @return The empty tab result
   * @see #EMPTY_RESULT
   */
  @NotNull
  @Contract(pure = true)
  public static TabResult empty() {
    return EMPTY_RESULT;
  }

  public List<String> getResult() {
    if (this.equals(EMPTY_RESULT)) {
      return new ArrayList<>();
    }
    return StringUtil.copyPartialMatches(token, completer, new ArrayList<>());
  }

  public String getToken() {
    return token;
  }

  public Iterable<String> getCompleter() {
    return completer;
  }
}