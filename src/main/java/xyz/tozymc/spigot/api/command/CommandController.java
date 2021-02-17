package xyz.tozymc.spigot.api.command;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.command.handler.CommandHandler;
import xyz.tozymc.spigot.api.command.handler.TabHandler;
import xyz.tozymc.spigot.api.command.wrapper.SimpleCommandMapWrapper;
import xyz.tozymc.spigot.api.util.Arrays;
import xyz.tozymc.spigot.api.util.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class controls the command flow.
 *
 * <p><b>Notes:</b> Must create an instance before use and {@link #addCommand(Command) register}
 * command in plugin {@link JavaPlugin#onEnable() loading}.
 *
 * <p><b>Notes:</b> The command registered outside plugin {@link JavaPlugin#onEnable() loading}
 * will not work (Not yet supported).
 *
 * @author TozyMC
 * @since 1.0
 */
public final class CommandController {

  private final JavaPlugin plugin;
  private final CommandHandler commandHandler;
  private final TabHandler tabHandler;
  private final String fallbackPrefix;
  private final Map<String, Command> rootCommands = new HashMap<>();
  private final Map<Command, List<Command>> commands = new HashMap<>();
  private final SimpleCommandMapWrapper commandMapWrapper;

  /**
   * Creates an instance of {@code CommandController} with default command handler and fall back
   * prefix.
   *
   * @param plugin The plugin instance
   */
  public CommandController(@NotNull JavaPlugin plugin) {
    this(plugin, (CommandHandler) null);
  }

  /**
   * Creates an instance of {@code CommandController} with default command handler and custom fall
   * back prefix.
   *
   * @param plugin         The plugin instance
   * @param fallbackPrefix A prefix which is prepended to the command with a ':' one or more times
   *                       to
   */
  public CommandController(@NotNull JavaPlugin plugin, @NotNull String fallbackPrefix) {
    this(plugin, null, fallbackPrefix);
  }


  /**
   * Creates an instance of {@code CommandController} with custom command handler and default fall
   * back prefix.
   *
   * @param plugin         The plugin instance
   * @param commandHandler The custom command handler
   */
  public CommandController(@NotNull JavaPlugin plugin, @Nullable CommandHandler commandHandler) {
    this(plugin, commandHandler, plugin.getDescription().getName());
  }

  /**
   * Creates an instance of {@code CommandController} with custom command handler and fall back
   * prefix.
   *
   * @param plugin         The plugin instance
   * @param commandHandler The custom command handler
   * @param fallbackPrefix A prefix which is prepended to the command with a ':' one or more times
   *                       to
   */
  public CommandController(@NotNull JavaPlugin plugin, @Nullable CommandHandler commandHandler,
      @NotNull String fallbackPrefix) {
    this.plugin = plugin;
    this.commandHandler = commandHandler == null ? new CommandHandler(this) : commandHandler;
    this.tabHandler = new TabHandler(this);
    this.fallbackPrefix = fallbackPrefix;
    this.commandMapWrapper = new SimpleCommandMapWrapper(this);
  }

  /**
   * Registers the command to controller. If command is root command ({@code command.getParent() ==
   * null}), the command will be also registered as Bukkit command.
   *
   * <p><b>Notes:</b> If {@code command} is registered as child command, it will not be registered
   * as root command.
   *
   * @param command The command to register
   * @return The command has registered
   * @throws IllegalStateException If command is registered or child command is root command, and
   *                               throws when root command is not registered.
   */
  @NotNull
  public Command addCommand(@NotNull Command command) {
    if (command.getRoot() == null) {
      Preconditions.checkState(registerPluginCommand(command), "Command %s is registered.",
          command.toString());
      return command;
    }
    Command root = command.getRoot();
    Preconditions.checkState(root.getRoot() == null,
        "Not support child command register as root command.");

    Preconditions.checkState(rootCommands.containsKey(root.getName()),
        "Root command %s must be registered first.", root.toString());

    return addChildCommand(command.getRoot(), command);
  }

  private boolean registerPluginCommand(@NotNull Command command) {
    rootCommands.put(command.getName(), command);
    return commandMapWrapper.registerPluginCommand(command);
  }

  @NotNull
  @Contract("_, _ -> param2")
  private Command addChildCommand(@NotNull Command parent, @NotNull Command child) {
    List<Command> subCommands = commands.getOrDefault(parent, new ArrayList<>());
    subCommands.add(child);
    commands.put(parent, subCommands);
    return child;
  }

  /**
   * Removes (unregisters) the command from this controller, it also unregister from Bukkit
   * command.
   *
   * <p><b>Notes:</b> If {@code command} you want to remove is the root command, it will remove all
   * children.</p>
   *
   * @param command The command need to remove
   * @return The command has been removed
   * @throws IllegalStateException If command is unregistered
   */
  @NotNull
  @Contract("_ -> param1")
  public Command removeCommand(@NotNull Command command) {
    if (command.getRoot() != null) {
      Preconditions.checkState(removeChildCommand(command), "Child command %s is unregistered.",
          command.toString());
      return command;
    }
    Preconditions.checkState(unregisterPluginCommand(command), "Command %s is unregistered.",
        command.toString());
    removeAllChildrenCommand(command);

    return command;
  }

  private boolean unregisterPluginCommand(@NotNull Command command) {
    rootCommands.remove(command.getName());
    return commandMapWrapper.unregisterPluginCommand(command);
  }

  private boolean removeChildCommand(@NotNull Command command) {
    return commands.get(command.getRoot()).remove(command);
  }

  private void removeAllChildrenCommand(Command root) {
    commands.remove(root);
  }

  @NotNull
  @Contract(value = "_ -> new", pure = true)
  public String[] deleteFirstArg(@NotNull String[] args) {
    return java.util.Arrays.copyOfRange(args, 1, args.length);
  }

  public boolean isFinalCommand(Command parent, String[] args) {
    return Arrays.isEmpty(args) || !commands.containsKey(parent);
  }

  @NotNull
  public Optional<Command> getCommand(Command root, String name) {
    return commands.get(root)
        .stream()
        .filter(command -> command.getName().equalsIgnoreCase(name))
        .findFirst();
  }

  @Nullable
  public Command getRootCommand(@NotNull String name) {
    return rootCommands.getOrDefault(name, null);
  }

  /**
   * Gets a prefix which is prepended to the command with a ':' one or more times to.
   *
   * @return The fall back prefix
   */
  @Contract(pure = true)
  @NotNull
  public String getFallbackPrefix() {
    return fallbackPrefix;
  }

  /**
   * Gets the owner of this CommandController
   *
   * @return Plugin that owns this command
   */
  @Contract(pure = true)
  public Plugin getPlugin() {
    return plugin;
  }

  /**
   * Gets {@link CommandHandler} that is implemented from {@link org.bukkit.command.CommandExecutor}.
   *
   * @return CommandHandler of this controller
   */
  @Contract(pure = true)
  public CommandHandler getCommandHandler() {
    return commandHandler;
  }

  /**
   * Gets {@link TabHandler} that is implemented from {@link org.bukkit.command.TabCompleter}.
   *
   * @return TabHandler of this controller
   */
  @Contract(pure = true)
  public TabHandler getTabHandler() {
    return tabHandler;
  }

  /**
   * Returns the list of all command, include root commands.
   *
   * @return The list of command
   */
  @NotNull
  public List<Command> getAllCommands() {
    List<Command> commandList = new ArrayList<>(rootCommands.values());
    commandList.addAll(this.commands.values()
        .stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList()));
    return commandList;
  }

  /**
   * Gets the map of command, with key is root command and value is the list of child command.
   *
   * @return The map of command
   */
  @Contract(pure = true)
  @NotNull
  public Map<Command, List<Command>> getCommands() {
    return commands;
  }
}
