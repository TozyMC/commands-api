package xyz.tozymc.spigot.api.util.bukkit.permission;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.util.Arrays;

import java.util.StringJoiner;

/**
 * The class provides the wrapper of bukkit permission, to check permission of object.
 *
 * @author TozyMC
 * @since 1.0
 */
public class PermissionWrapper {

  private final String permission;

  @Contract(pure = true)
  private PermissionWrapper(@NotNull String permission) {
    this.permission = permission;
  }

  /**
   * Creates permission wrapper form parent wrapper and child permission.
   *
   * @param parent     The parent permission wrapper
   * @param permission The child permission
   * @return The wrapper of permission
   */
  public static @NotNull
  PermissionWrapper of(@NotNull PermissionWrapper parent,
      @NotNull String permission) {
    return of(parent.permission, permission);
  }

  /**
   * Creates permission wrapper from permission nodes.
   *
   * <p>This method join permission nodes into one permission string, e.g {@code {"a","b","c}}
   * will join into {@code "a.b.c"}</p>
   *
   * @param nodes An array of permission node
   * @return The wrapper of permission
   */
  public static @NotNull
  PermissionWrapper of(@NotNull String... nodes) {
    StringJoiner joiner = new StringJoiner(".");
    return of(Arrays.joinToString(joiner, nodes));
  }

  /**
   * Creates permission wrapper with permission string.
   *
   * @param permission The permission string
   * @return The wrapper of permission
   */
  @Contract("_ -> new")
  public static @NotNull
  PermissionWrapper of(@NotNull String permission) {
    return new PermissionWrapper(permission);
  }

  /**
   * Checks player's permission and returns the result.
   *
   * @param player The player
   * @return true if has permission, otherwise false
   */
  public boolean playerHas(@NotNull Player player) {
    return check(player);
  }

  /**
   * Checks sender's permission and returns the result.
   *
   * @param sender The command sender
   * @return true if has permission, otherwise false
   */
  public boolean has(@NotNull CommandSender sender) {
    return check(sender);
  }

  /**
   * Checks the sender's permissions and returns true if the sender doesn't have one, otherwise
   * false.
   *
   * <p><b>Note:</b> Usually used in reverse conditions.</p>
   *
   * @param sender The command sender
   * @return false if has permission, otherwise true
   */
  public boolean notHas(@NotNull CommandSender sender) {
    return !has(sender);
  }

  /**
   * Checks entity's permission and returns the result.
   *
   * @param entity The command sender
   * @return true if has permission, otherwise false
   */
  public boolean entityHas(@NotNull Entity entity) {
    return check(entity);
  }

  private boolean check(Permissible permissible) {
    if (permissible instanceof ConsoleCommandSender) {
      return true;
    }

    if (permissible.isOp()) {
      return true;
    }

    return permissible.hasPermission("*") || permissible.hasPermission(permission);
  }

  /**
   * Gets permission string of the wrapper.
   *
   * @return The permission string
   */
  @NotNull
  public String getPermission() {
    return this.permission;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (!(o instanceof PermissionWrapper)) {
      return false;
    }
    PermissionWrapper other = (PermissionWrapper) o;
    return other.permission.equals(permission);
  }

  @Override
  public int hashCode() {
    int PRIME = 59;
    return PRIME + permission.hashCode();
  }

  @Override
  public String toString() {
    return "PermissionWrapper(permission=" + permission + ")";
  }
}
