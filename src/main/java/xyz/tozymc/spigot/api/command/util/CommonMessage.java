package xyz.tozymc.spigot.api.command.util;

import static xyz.tozymc.spigot.api.util.bukkit.Colors.color;

import org.jetbrains.annotations.Contract;
import xyz.tozymc.spigot.api.command.Command;
import xyz.tozymc.spigot.api.util.Arrays;

/**
 * Common messages use in command handler.
 *
 * @since 1.0
 */
public final class CommonMessage {

  private static String[] noPermissions = {"&cYou don't have permission to do that."};
  private static String[] noPermission = {"&cYou don't have permission {permission} to do that."};
  private static String[] notConsole = {"&cRequire console to execute this command."};
  private static String[] notPlayer = {"&cYou must be player to execute this command!"};
  private static String[] syntaxUsage = {"&cUsage: &f{syntax} &7- {description}"};

  @Contract(pure = true)
  private CommonMessage() {
    color(noPermissions);
    color(noPermission);
    color(notConsole);
    color(notPlayer);
    color(syntaxUsage);
  }

  /**
   * Gets no-permission messages with unknowns permission.
   *
   * @return An array of text
   */
  @Contract(pure = true)
  public static String[] getNoPermissions() {
    return noPermissions;
  }

  /**
   * Sets no-permission messages with unknowns permission.
   *
   * @param noPermissions An array of text
   */
  public static void setNoPermissions(String... noPermissions) {
    color(noPermissions);
    CommonMessage.noPermissions = noPermissions;
  }

  /**
   * Gets no-permission messages.
   *
   * <p><b>Placeholder:</b> {@code {permission}} - Will be replaced to permission string.</p>
   *
   * @return An array of text
   */
  @Contract(pure = true)
  public static String[] getNoPermission() {
    return noPermission;
  }

  /**
   * Sets no-permission messages.
   *
   * @param noPermission An array of text
   */
  public static void setNoPermission(String... noPermission) {
    color(noPermission);
    CommonMessage.noPermission = noPermission;
  }

  /**
   * Performs placeholder and returns the no-permission messages has been formatted.
   *
   * @param permission The permission string
   * @return The texts has been formatted permission
   */
  public static String[] getNoPermission(String permission) {
    String[] texts = CommonMessage.noPermission.clone();
    Arrays.setAll(texts, i -> texts[i].replaceAll("\\{permission}", permission));
    color(texts);
    return texts;
  }

  /**
   * Gets not-console messages.
   *
   * @return An array of text
   */
  @Contract(pure = true)
  public static String[] getNotConsole() {
    return notConsole;
  }

  /**
   * Sets not-console messages.
   *
   * @param notConsole An array of text
   */
  public static void setNotConsole(String... notConsole) {
    color(notConsole);
    CommonMessage.notConsole = notConsole;
  }

  /**
   * Gets not-player messages.
   *
   * @return An array of text
   */
  @Contract(pure = true)
  public static String[] getNotPlayer() {
    return notPlayer;
  }

  /**
   * Sets not-player messages.
   *
   * @param notPlayer An array of text
   */
  public static void setNotPlayer(String... notPlayer) {
    color(notPlayer);
    CommonMessage.notPlayer = notPlayer;
  }

  /**
   * Gets syntax usage messages.
   * <p>
   * <b>Placeholders:</b>
   * <ul>
   *   <li>{@code {syntax}} - The command syntax.</li>
   *   <li>{@code {description}} - The brief description of the command.</li>
   * </ul>
   *
   * @return An array of text
   */
  @Contract(pure = true)
  public static String[] getSyntaxUsage() {
    return syntaxUsage;
  }

  /**
   * Sets syntax usage messages.
   *
   * @param syntaxUsage An array of text
   */
  public static void setSyntaxUsage(String... syntaxUsage) {
    color(syntaxUsage);
    CommonMessage.syntaxUsage = syntaxUsage;
  }

  /**
   * Performs placeholder and returns the syntax usage messages has been formatted.
   *
   * @param command The command for formatter
   * @return The texts has been formatted
   */
  public static String[] getSyntaxUsage(Command command) {
    String[] texts = CommonMessage.syntaxUsage;
    Arrays.setAll(texts, i -> texts[i].replaceAll("\\{syntax}", command.getSyntax()));
    Arrays.setAll(texts, i -> texts[i].replaceAll("\\{description}", command.getDescription()));
    color(texts);
    return texts;
  }
}
