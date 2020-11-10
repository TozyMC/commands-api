package xyz.tozymc.spigot.api.command;

import com.google.common.base.Preconditions;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.command.handler.CommandHandler;
import xyz.tozymc.spigot.api.command.handler.TabHandler;
import xyz.tozymc.spigot.api.util.Arrays;

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
  private final Map<String, Command> rootCommands = new HashMap<>();
  private final Map<Command, List<Command>> commands = new HashMap<>();

  /**
   * * Creates an instance of {@code CommandController} with default command handler.
   *
   * @param plugin The plugin instance
   */
  public CommandController(@NotNull JavaPlugin plugin) {
    this(plugin, null);
  }

  /**
   * Creates an instance of {@code CommandController} with custom command handler.
   *
   * @param plugin         The plugin instance
   * @param commandHandler The custom command handler
   */
  public CommandController(@NotNull JavaPlugin plugin, @Nullable CommandHandler commandHandler) {
    this.plugin = plugin;
    this.commandHandler = commandHandler == null ? new CommandHandler(this) : commandHandler;
    this.tabHandler = new TabHandler(this);
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

  @NotNull
  @Contract("_ -> param1")
  private Command registerPluginCommand(@NotNull Command command) {
    String name = command.getName();
    rootCommands.put(name, command);
    PluginCommand pluginCmd = Preconditions.checkNotNull(plugin.getCommand(name),
        "Command %s need register in plugin.yml", name);
    pluginCmd.setExecutor(commandHandler);
    pluginCmd.setAliases(command.getAliases())
        .setDescription(command.getDescription());

    pluginCmd.setTabCompleter(tabHandler);
    return command;
  }

  @NotNull
  private Command addSubCommand(@NotNull Command parent, @NotNull Command child) {
    List<Command> subCommands = commands.getOrDefault(parent, new ArrayList<>());
    subCommands.add(child);
    commands.put(parent, subCommands);
    return child;
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
   */
  @NotNull
  public Command addCommand(@NotNull Command command) {
    if (command.getParent() == null) {
      return registerPluginCommand(command);
    }
    Preconditions.checkState(command.getParent().getParent() == null,
        "Not support child command registered as root command.");

    return addSubCommand(command.getParent(), command);
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
