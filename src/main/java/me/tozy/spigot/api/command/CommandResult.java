package me.tozy.spigot.api.command;

import me.tozy.spigot.api.command.util.CommonMessage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * As a result of the command, it is generated when the command is executed and returns the result.
 *
 * @author TozyMC
 * @since 1.0
 */
public class CommandResult {

  private final Type type;
  private final String[] params;

  @Contract(pure = true)
  private CommandResult(@NotNull Type type, @NotNull String[] params) {
    this.type = type;
    this.params = params;
  }

  /**
   * Creates new command result with type and additional parameters.
   *
   * <p><b>Notes:</b> {@code params} can difference with difference {@code type}.</p>
   * <p><b>See:</b> {@link Type} to have exact additional parameters passed.</p>
   *
   * @param type   Result type of command
   * @param params Additional parameters for the results
   * @return The result of command
   */
  @Contract(value = "_, _ -> new", pure = true)
  @NotNull
  public static CommandResult from(Type type, String... params) {
    return new CommandResult(type, params);
  }

  @NotNull
  public Type getType() {
    return type;
  }

  @NotNull
  public String[] getParams() {
    return params;
  }

  public enum Type {
    /**
     * The command executed unsuccessfully.
     *
     * <p><b>Additional parameters:</b> {@code strings} - messages to send to command sender.</p>
     */
    FAILURE(false),
    /**
     * Prevent the command from executing because the sender has no permissions.
     * <p>
     * <b>Additional parameters:</b>
     * <ul>
     *   <li>{@code null} or empty - Sends no-permission messages with unknowns permission ({@link CommonMessage#getNoPermissions() NoPermissions}).</li>
     *   <li>One parameter - Sends no-permission messages with permission passed in parameter one ({@link CommonMessage#getNoPermission() NoPermission}).</li>
     *   <li>More than 1 parameter - Unsupported.</li>
     * </ul>
     */
    NO_PERMISSION(false),
    /**
     * The command executed successfully.
     *
     * <p><b>Additional parameters:</b> {@code strings} - messages to send to command sender.</p>
     */
    SUCCESS(true),
    /**
     * Wrong syntax or parameters passed to the command.
     */
    WRONG_SYNTAX(false);

    private final boolean result;

    Type(boolean result) {
      this.result = result;
    }

    public boolean getResult() {
      return result;
    }
  }
}
