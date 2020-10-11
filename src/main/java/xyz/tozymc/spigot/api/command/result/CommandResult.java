package xyz.tozymc.spigot.api.command.result;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.command.util.CommonMessage;

/**
 * As a result of the command, it is generated when the command is executed and returns the result.
 *
 * @author TozyMC
 * @since 1.0
 */
public class CommandResult {

  /**
   * NoPermissions result without permission string parameter.
   */
  public static final CommandResult NO_PERMISSIONS = new CommandResult(Type.NO_PERMISSION);
  /**
   * Success result with out any messages response.
   */
  public static final CommandResult SUCCESS = new CommandResult(Type.SUCCESS);
  /**
   * Failure result with out any messages response.
   *
   * @see #unknown()
   */
  public static final CommandResult FAILURE = new CommandResult(Type.FAILURE);
  /**
   * Wrong syntax messages will be sent.
   */
  public static final CommandResult WRONG_SYNTAX = new CommandResult(Type.WRONG_SYNTAX);


  private final Type type;
  private final String[] params;

  @Contract(pure = true)
  private CommandResult(@NotNull Type type, @Nullable String... params) {
    this.type = type;
    this.params = params;
  }

  /**
   * Creates new command result with type and additional parameters.
   *
   * <p><b>Notes:</b> {@code params} can difference with difference {@code type}.
   *
   * <p><b>See:</b> {@link Type} to have exact additional parameters passed.
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

  /**
   * Similar {@link #FAILURE} but another naming.
   *
   * @return The unknown result of command
   * @see #FAILURE
   */
  @NotNull
  @Contract(pure = true)
  public static CommandResult unknown() {
    return FAILURE;
  }

  @NotNull
  public Type getType() {
    return type;
  }

  @Nullable
  public String[] getParams() {
    return params;
  }

  public enum Type {
    /**
     * The command executed unsuccessfully or unknowns.
     *
     * <p><b>Additional parameters:</b> {@code strings} - messages to send to command sender.
     */
    FAILURE(false),
    /**
     * Prevent the command from executing because the sender has no permissions.
     *
     * <p><b>Additional parameters:</b>
     * <dl>
     *   <dt>{@code null} or empty</dt>
     *   <dd>Sends no-permission messages with unknowns permission ({@link CommonMessage#getNoPermissions() NoPermissions}).</dd>
     *   <dt>One parameter</dt>
     *   <dd>Sends no-permission messages with permission passed in parameter one ({@link CommonMessage#getNoPermission() NoPermission}).</dd>
     *   <dt>More than 1 parameter</dt>
     *   <dd>Unsupported.</dd>
     * </dl>
     */
    NO_PERMISSION(false),
    /**
     * The command executed successfully.
     *
     * <p><b>Additional parameters:</b> {@code strings} - messages to send to command sender.
     */
    SUCCESS(true),
    /**
     * Wrong syntax or parameters passed to the command.
     *
     * <p>You can change messages in {@link CommonMessage#setSyntaxUsage(String...)}
     * <p><b>Additional parameters:</b> {@code none} - Any parameter passed won't be used.
     *
     * @see CommandResult#WRONG_SYNTAX
     */
    WRONG_SYNTAX(false);

    private final boolean result;

    Type(boolean result) {
      this.result = result;
    }

    public boolean asBoolean() {
      return result;
    }
  }
}
