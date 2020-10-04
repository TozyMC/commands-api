package me.tozy.spigot.api.command;

import me.tozy.spigot.api.util.bukkit.permission.PermissionWrapper;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents a command which use in command handler.
 *
 * @author TozyMC
 * @see AbstractCommand
 * @see CombinedCommand
 * @see ConsoleCommand
 * @see PlayerCommand
 * @since 1.0
 */
public interface Command {

  /**
   * Executes given command, returns the results.
   *
   * <p><b>Note:</b> Command only executes by a player.</p>
   *
   * @param player Player executed command
   * @param params Passed command parameters
   * @return Command's result was executed
   */
  @NotNull
  CommandResult onCommand(@NotNull Player player, @NotNull String[] params);

  /**
   * Executes given command, returns the results.
   *
   * <p><b>Note:</b> Command only executes in the console.</p>
   *
   * @param console Console sender executed command
   * @param params  Passed command parameters
   * @return Command's result was executed
   */
  @NotNull
  CommandResult onConsoleCommand(@NotNull ConsoleCommandSender console,
      @NotNull String[] params);

  /**
   * Requests a list of possible completions for a command parameters.
   *
   * <p><b>Note:</b> Request will be executed if command were executed by a player.</p>
   *
   * @param player Player executed command
   * @param params The parameters pass to the to the command, including final partial parameter to
   *               be completed and command label
   * @return A List of possible completions for the final argument, or an empty list to default to
   * the command executor
   */
  @NotNull
  List<String> onTab(@NotNull Player player, @NotNull String[] params);

  /**
   * Requests a list of possible completions for a command parameters.
   *
   * <p><b>Note:</b> Request will be executed if command was executed in the console.</p>
   *
   * @param console Console sender executed command
   * @param params  The parameters pass to the to the command, including final partial parameter to
   *                be completed and command label
   * @return A List of possible completions for the final argument, or an empty list to default to
   * the command executor
   */
  @NotNull
  List<String> onConsoleTab(@NotNull ConsoleCommandSender console,
      @NotNull String[] params);

  /**
   * Returns parent {@link Command} of this command.
   *
   * @return Parent of this command
   */
  @Nullable
  Command getParent();

  /**
   * Returns the name of this command.
   *
   * @return Name of this command
   */
  @NotNull
  String getName();

  /**
   * Returns the {@link PermissionWrapper} of this command
   *
   * @return The permission wrapper of this command
   */
  @NotNull
  PermissionWrapper getPermission();

  /**
   * Get the syntax or example usage of this command.
   *
   * @return Syntax of this command
   */
  @NotNull
  String getSyntax();

  /**
   * Gets a brief description of this command
   *
   * @return Description of this command
   */
  @NotNull
  String getDescription();

  /**
   * Returns a list of active aliases of this command, include value of {@link #getName()} method.
   *
   * @return List of aliases
   */
  @NotNull
  List<String> getAliases();
}
