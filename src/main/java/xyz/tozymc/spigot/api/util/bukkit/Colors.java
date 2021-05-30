package xyz.tozymc.spigot.api.util.bukkit;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for color the text in Bukkit.
 *
 * @author TozyMC
 * @since 1.0
 */
public final class Colors {

  private Colors() {}

  /**
   * Translates a text using {@code &} character into a string that uses the internal {@link
   * ChatColor#COLOR_CHAR} character.
   *
   * @param text The original text
   * @return The text has been colored
   */
  @NotNull
  @Contract("_ -> new")
  public static String color(@NotNull String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  /**
   * Translates all element of text array using {@code &} character into the internal {@link
   * ChatColor#COLOR_CHAR} character.
   *
   * @param texts The original texts
   * @return The texts containing element has been colored
   */
  @Contract("_ -> param1")
  public static String[] color(@NotNull String... texts) {
    Arrays.setAll(texts, i -> color(texts[i]));
    return texts;
  }

  /**
   * Translates all element of text list using {@code &} character into the internal {@link
   * ChatColor#COLOR_CHAR} character.
   *
   * @param list The original list of text
   * @return The list containing element has been colored
   */
  public static List<String> color(List<String> list) {
    Lists.setAll(list, Colors::color);
    return list;
  }
}
