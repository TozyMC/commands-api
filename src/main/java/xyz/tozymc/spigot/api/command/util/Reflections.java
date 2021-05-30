package xyz.tozymc.spigot.api.command.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author TozyMC
 * @since 2.0
 */
public final class Reflections {

  private static final MethodHandle PLUGIN_COMMAND_CONSTRUCTOR;
  private static final MethodHandle COMMAND_MAP_FIELD;
  private static final MethodHandle KNOWN_COMMANDS_FIELD;

  static {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodHandle plCmdConstructor = null;
    MethodHandle commandMapField = null;
    MethodHandle knownCommandsField = null;
    try {
      Constructor<PluginCommand> reflectConstructor = PluginCommand.class
          .getDeclaredConstructor(String.class, Plugin.class);
      reflectConstructor.setAccessible(true);
      plCmdConstructor = lookup.unreflectConstructor(reflectConstructor);

      Class<?> serverClass = Bukkit.getServer().getClass();
      Field commandMapReflect = serverClass.getDeclaredField("commandMap");
      commandMapReflect.setAccessible(true);
      commandMapField = lookup.unreflectGetter(commandMapReflect);

      Field knownCommandsReflect = SimpleCommandMap.class.getDeclaredField("knownCommands");
      knownCommandsReflect.setAccessible(true);
      knownCommandsField = lookup.unreflectGetter(knownCommandsReflect);
    } catch (Exception ignored) {
    }

    PLUGIN_COMMAND_CONSTRUCTOR = plCmdConstructor;
    COMMAND_MAP_FIELD = commandMapField;
    KNOWN_COMMANDS_FIELD = knownCommandsField;
  }

  private Reflections() {}

  /**
   * Creates new {@link PluginCommand} instance.
   *
   * @param name  The command name
   * @param owner The plugin that is owner this command
   * @return The PluginCommand instance
   */
  @Nullable
  public static PluginCommand newPluginCommand(@NotNull String name, @NotNull Plugin owner) {
    try {
      return (PluginCommand) PLUGIN_COMMAND_CONSTRUCTOR.invoke(name, owner);
    } catch (Throwable throwable) {
      return null;
    }
  }

  /**
   * Gets {@link SimpleCommandMap} from bukkit server.
   *
   * @param server The bukkit server
   * @return The command map
   */
  @Contract(pure = true)
  public static SimpleCommandMap getCommandMap(Server server) {
    try {
      return (SimpleCommandMap) COMMAND_MAP_FIELD.invoke(server);
    } catch (Throwable throwable) {
      return null;
    }
  }

  /**
   * Gets {@code knownCommands} field in {@link SimpleCommandMap}.
   *
   * @param commandMap The SimpleCommandMap instance
   * @return The knownCommands in SimpleCommandMap
   */
  public static Map<String, Command> getKnownCommands(SimpleCommandMap commandMap) {
    try {
      //noinspection unchecked
      return (Map<String, Command>) KNOWN_COMMANDS_FIELD.invoke(commandMap);
    } catch (Throwable throwable) {
      return null;
    }
  }
}
