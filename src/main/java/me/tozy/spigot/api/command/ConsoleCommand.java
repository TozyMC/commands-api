package me.tozy.spigot.api.command;

import me.tozy.spigot.api.command.CommandResult.Type;
import me.tozy.spigot.api.command.util.CommonMessage;
import me.tozy.spigot.api.util.bukkit.permission.PermissionWrapper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface, to minimize the effort
 * required to implement this interface for console commands.
 *
 * @author TozyMC
 * @since 1.0
 */
public abstract class ConsoleCommand extends AbstractCommand {

  protected ConsoleCommand(@Nullable Command parent, @NotNull String name,
      @NotNull List<String> aliases) {
    super(parent, name, aliases);
  }

  protected ConsoleCommand(@Nullable Command parent, @NotNull String name,
      @NotNull String... aliases) {
    super(parent, name, aliases);
  }

  protected ConsoleCommand(@NotNull Command parent, @NotNull String name) {
    super(parent, name);
  }

  protected ConsoleCommand(@NotNull String name) {
    super(name);
  }

  protected ConsoleCommand(@NotNull String name, @NotNull String... aliases) {
    super(name, aliases);
  }

  protected ConsoleCommand(@NotNull String name, @NotNull List<String> aliases) {
    super(name, aliases);
  }

  @NotNull
  @Override
  public CommandResult onCommand(@NotNull Player player, @NotNull String[] params) {
    return CommandResult.from(Type.FAILURE, CommonMessage.getNotConsole());
  }

  @NotNull
  @Override
  public List<String> onTab(@NotNull Player player, @NotNull String[] params) {
    return new ArrayList<>();
  }

  @NotNull
  @Override
  public PermissionWrapper getPermission() {
    return PermissionWrapper.of("");
  }
}
