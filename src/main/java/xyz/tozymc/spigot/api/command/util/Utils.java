package xyz.tozymc.spigot.api.command.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public final class Utils {

  private static final MethodHandle PLUGIN_COMMAND_CONSTRUCTOR;
  private static final MethodHandle COMMAND_MAP_FIELD;

  static {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodHandle plCmdConstructor = null;
    MethodHandle commandMapField = null;
    try {
      MethodType plCmdMethodType = MethodType.methodType(void.class, String.class, Plugin.class);
      plCmdConstructor = lookup.findConstructor(PluginCommand.class, plCmdMethodType);

      Class<?> serverClass = Bukkit.getServer().getClass();
      commandMapField = lookup.findGetter(serverClass, "commandMap", SimpleCommandMap.class);
    } catch (Exception ignored) {
    }

    PLUGIN_COMMAND_CONSTRUCTOR = plCmdConstructor;
    COMMAND_MAP_FIELD = commandMapField;
  }

  private Utils() {
  }

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
  @Nullable
  @Contract(pure = true)
  public static SimpleCommandMap getCommandMap(Server server) {
    try {
      return (SimpleCommandMap) COMMAND_MAP_FIELD.invoke(server);
    } catch (Throwable throwable) {
      return null;
    }
  }
}
