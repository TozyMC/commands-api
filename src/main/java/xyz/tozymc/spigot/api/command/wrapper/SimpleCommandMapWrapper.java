package xyz.tozymc.spigot.api.command.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.command.AbstractCommand;
import xyz.tozymc.spigot.api.command.Command;
import xyz.tozymc.spigot.api.command.CommandController;
import xyz.tozymc.spigot.api.command.util.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A wrapper class to communicate {@code commandMap} field in {@code CraftServer} instance.
 *
 * @author TozyMC
 * @since 2.0
 */
public final class SimpleCommandMapWrapper {

  private final CommandController controller;
  private final String fallbackPrefix;
  private final SimpleCommandMap commandMap;
  private final Map<String, org.bukkit.command.Command> knownCommands;

  public SimpleCommandMapWrapper(@NotNull CommandController controller) {
    this.controller = controller;
    this.fallbackPrefix = controller.getFallbackPrefix();
    this.commandMap = Reflections.getCommandMap(Bukkit.getServer());
    this.knownCommands = Reflections.getKnownCommands(commandMap);
  }

  public boolean registerPluginCommand(@NotNull Command command) {
    if (knownCommands.containsValue(getPluginCommand(command))) {
      return false;
    }
    PluginCommand plCmd = AbstractCommand.asPluginCommandCopy(command, controller);
    return this.register(plCmd);
  }

  private boolean register(PluginCommand command) {
    return commandMap.register(fallbackPrefix, command);
  }

  public boolean unregisterPluginCommand(Command command) {
    PluginCommand plCmd = getPluginCommand(command);
    if (!this.unregister(plCmd)) {
      return false;
    }
    return plCmd.unregister(commandMap);
  }

  private synchronized boolean unregister(PluginCommand command) {
    if (!knownCommands.containsValue(command)) {
      return false;
    }
    List<String> registered = new ArrayList<>();
    knownCommands.forEach((label, comm) -> {
      if (comm.equals(command)) {
        registered.add(label);
      }
    });
    registered.forEach(knownCommands::remove);
    return !registered.isEmpty();
  }

  public PluginCommand getPluginCommand(@NotNull Command command) {
    return (PluginCommand) knownCommands.get(fallbackPrefix + ":" + command.getName());
  }
}
