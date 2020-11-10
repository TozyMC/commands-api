package xyz.tozymc.spigot.api.command;

import org.bukkit.command.ConsoleCommandSender;
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

  public PlayerCommand(@Nullable Command parent, @NotNull String name,
      @NotNull List<String> aliases) {
    super(parent, name, aliases);
  }

  public PlayerCommand(@Nullable Command parent, @NotNull String name,
      @NotNull String... aliases) {
    super(parent, name, aliases);
  }

  public PlayerCommand(@NotNull Command parent, @NotNull String name) {
    super(parent, name);
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
  public CommandResult onConsoleCommand(@NotNull ConsoleCommandSender console,
      @NotNull String[] params) {
    return CommandResult.from(Type.FAILURE, CommonMessage.getNotPlayer());
  }

  @Override
  @NotNull
  public TabResult onConsoleTab(@NotNull ConsoleCommandSender console,
      @NotNull String[] params) {
    return TabResult.empty();
  }
}
