package me.tozy.spigot.api.command.util;

import org.jetbrains.annotations.Contract;

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
    CommonMessage.noPermission = noPermission;
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
    CommonMessage.syntaxUsage = syntaxUsage;
  }
}
