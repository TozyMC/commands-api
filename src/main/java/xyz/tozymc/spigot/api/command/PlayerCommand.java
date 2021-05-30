package xyz.tozymc.spigot.api.command;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.command.result.CommandResult;
import xyz.tozymc.spigot.api.command.result.CommandResult.Type;
import xyz.tozymc.spigot.api.command.result.TabResult;
import xyz.tozymc.spigot.api.command.util.CommonMessage;

import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface, to minimize the effort
 * required to implement this interface for player commands.
 *
 * @author TozyMC
 * @since 1.0
 */
public abstract class PlayerCommand extends AbstractCommand {

  private static final CommandResult NOT_PLAYER_COMMAND_RESULT;

  static {
    NOT_PLAYER_COMMAND_RESULT = CommandResult.from(Type.FAILURE, CommonMessage.getNotPlayer());
  }


  public PlayerCommand(@Nullable Command root, @NotNull String name,
      @NotNull List<String> aliases) {
    super(root, name, aliases);
  }

  public PlayerCommand(@Nullable Command root, @NotNull String name,
      @NotNull String... aliases) {
    super(root, name, aliases);
  }

  public PlayerCommand(@NotNull Command root, @NotNull String name) {
    super(root, name);
  }

  public PlayerCommand(@NotNull String name) {
    super(name);
  }

  public PlayerCommand(@NotNull String name,
      @NotNull String... aliases) {
    super(name, aliases);
  }

  public PlayerCommand(@NotNull String name, @NotNull List<String> aliases) {
    super(name, aliases);
  }


  @NotNull
  @Override
  public abstract CommandResult onCommand(@NotNull Player player, @NotNull String[] params);

  @NotNull
  @Override
  public abstract TabResult onTab(@NotNull Player player, @NotNull String[] params);

  /**
   * Executes given command, returns the results.
   *
   * <p>Default implementation to prevent console from executing player commands.
   *
   * @param console Console sender executed command
   * @param params  Passed command parameters
   * @return An failure result with NotPlayer message.
   */
  @NotNull
  @Override
  public CommandResult onConsoleCommand(@NotNull ConsoleCommandSender console,
      @NotNull String[] params) {
    return NOT_PLAYER_COMMAND_RESULT;
  }

  /**
   * Requests a list of possible completions for a command parameters.
   *
   * <p>Default implementation to prevent console from performing player completions for a command
   * parameter.
   *
   * @param console Console sender executed command
   * @param params  The parameters pass to the to the command, including final partial parameter to
   *                be completed and command label
   * @return An empty tab result.
   */
  @Override
  @NotNull
  public TabResult onConsoleTab(@NotNull ConsoleCommandSender console,
      @NotNull String[] params) {
    return TabResult.empty();
  }
}
