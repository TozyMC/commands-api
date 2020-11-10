package xyz.tozymc.spigot.api.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a skeletal implementation of the Command interface.
 *
 * @author TozyMC
 * @see CombinedCommand
 * @see PlayerCommand
 * @see ConsoleCommand
 * @since 1.0
 */
public abstract class AbstractCommand implements Command {

  protected final Command parent;
  protected final String name;
  private final List<String> aliases;

  /**
   * A command with full specific {@code parent}, {@code name}, {@code aliases}.
   *
   * <p>If {@code parent} is null, this command will be parent which registered as bukkit
   * command.</p>
   *
   * @param parent  Parent of this command
   * @param name    Name of this command
   * @param aliases List of aliases
   */
  public AbstractCommand(@Nullable Command parent, @NotNull String name,
      @NotNull List<String> aliases) {
    this.parent = parent;
    this.name = name;
    this.aliases = Lists.addAll(aliases, name);
  }

  /**
   * A command with full specific {@code parent}, {@code name}, {@code aliases}.
   *
   * @param parent  Parent of this command
   * @param name    Name of this command
   * @param aliases Array of aliases
   * @see #AbstractCommand(Command, String, List)
   */
  public AbstractCommand(@Nullable Command parent, @NotNull String name,
      @NotNull String... aliases) {
    this(parent, name, Lists.newArrayList(aliases));
  }

  /**
   * A command without the specified {@code aliases}.
   *
   * @param parent Parent of this command
   * @param name   Name of this command
   * @see #AbstractCommand(Command, String, List)
   */
  public AbstractCommand(@NotNull Command parent, @NotNull String name) {
    this(parent, name, new ArrayList<>());
  }

  /**
   * Defaults {@code parent} to null, create a parent-command instance without {@code aliases}.
   *
   * @param name Name of this command
   */
  public AbstractCommand(@NotNull String name) {
    this(null, name, new ArrayList<>());
  }

  /**
   * Defaults {@code parent} to null, create a parent-command instance.
   *
   * @param name    Name of this command
   * @param aliases Array of aliases
   */
  public AbstractCommand(@NotNull String name, @NotNull String... aliases) {
    this(null, name, aliases);
  }

  /**
   * Defaults {@code parent} to null, create a parent-command instance.
   *
   * @param name    Name of this command
   * @param aliases List of aliases
   */
  public AbstractCommand(@NotNull String name, @NotNull List<String> aliases) {
    this(null, name, aliases);
  }

  @Override
  public @Nullable
  Command getParent() {
    return parent;
  }

  @Override
  public @NotNull
  String getName() {
    return name;
  }

  @Override
  public @NotNull
  List<String> getAliases() {
    return aliases;
  }
}
