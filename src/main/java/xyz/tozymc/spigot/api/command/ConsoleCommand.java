package xyz.tozymc.spigot.api.command;

import xyz.tozymc.spigot.api.command.result.CommandResult;
import xyz.tozymc.spigot.api.command.result.CommandResult.Type;
import xyz.tozymc.spigot.api.command.result.TabResult;
import xyz.tozymc.spigot.api.command.util.CommonMessage;
import xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface, to minimize the effort
 * required to implement this interface for console commands.
 *
 * @author TozyMC
 * @since 1.0
 */
public abstract class ConsoleCommand extends AbstractCommand {

  public ConsoleCommand(@Nullable Command parent, @NotNull String name,
      @NotNull List<String> aliases) {
    super(parent, name, aliases);
  }

  public ConsoleCommand(@Nullable Command parent, @NotNull String name,
      @NotNull String... aliases) {
    super(parent, name, aliases);
  }

  public ConsoleCommand(@NotNull Command parent, @NotNull String name) {
    super(parent, name);
  }

  public ConsoleCommand(@NotNull String name) {
    super(name);
  }

  public ConsoleCommand(@NotNull String name, @NotNull String... aliases) {
    super(name, aliases);
  }

  public ConsoleCommand(@NotNull String name, @NotNull List<String> aliases) {
    super(name, aliases);
  }

  @NotNull
  @Override
  public CommandResult onCommand(@NotNull Player player, @NotNull String[] params) {
    return CommandResult.from(Type.FAILURE, CommonMessage.getNotConsole());
  }

  @NotNull
  @Override
  public TabResult onTab(@NotNull Player player, @NotNull String[] params) {
    return TabResult.empty();
  }

  @NotNull
  @Override
  public PermissionWrapper getPermission() {
    return PermissionWrapper.of("");
  }
}
