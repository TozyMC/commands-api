package xyz.tozymc.spigot.api.util.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.util.Lists;

/**
 * Utility class for color the text in Bukkit.
 *
 * @author TozyMC
 * @since 1.0
 */
public final class Colors {
  private static final Pattern HEX_PATTERN = Pattern.compile("&(#[a-fA-F0-9]{6})");
  private static final MethodHandle CHAT_COLOR_OF_METHOD;

  static {
    MethodHandle chatColorOfMethod = null;
    try {
      chatColorOfMethod = MethodHandles.lookup()
          .findStatic(ChatColor.class, "of", MethodType.methodType(ChatColor.class, String.class));
    } catch (ReflectiveOperationException ignored) {
    }
    CHAT_COLOR_OF_METHOD = chatColorOfMethod;
  }

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
    return hex(text);
  }

  private static String hex(String text) {
    String coloredText = ChatColor.translateAlternateColorCodes('&', text);
    if (CHAT_COLOR_OF_METHOD == null) {
      return coloredText;
    }

    Matcher matcher = HEX_PATTERN.matcher(coloredText);
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      replaceHex(buffer, matcher);
    }
    return matcher.appendTail(buffer).toString();
  }

  private static void replaceHex(StringBuffer buffer, Matcher matcher) {
    if (CHAT_COLOR_OF_METHOD == null) {
      matcher.appendReplacement(buffer, "");
      return;
    }
    try {
      matcher.appendReplacement(buffer,
          ((ChatColor) CHAT_COLOR_OF_METHOD.invoke(matcher.group(1))).toString());
    } catch (Throwable ignored) {
    }
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
