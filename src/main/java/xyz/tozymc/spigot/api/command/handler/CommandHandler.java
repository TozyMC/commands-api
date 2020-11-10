package xyz.tozymc.spigot.api.command.handler;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.command.Command;
import xyz.tozymc.spigot.api.command.CommandController;
import xyz.tozymc.spigot.api.command.result.CommandResult;
import xyz.tozymc.spigot.api.command.util.CommonMessage;
import xyz.tozymc.spigot.api.util.Arrays;
import xyz.tozymc.spigot.api.util.bukkit.Colors;

import java.util.Optional;

public class CommandHandler implements CommandExecutor {

  protected final CommandController controller;

  public CommandHandler(CommandController controller) {
    this.controller = controller;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender,
      @NotNull org.bukkit.command.Command bukkitCmd,
      @NotNull String label,
      @NotNull String[] args) {
    Command root = controller.getRootCommand(bukkitCmd.getName());
    if (root == null) {
      return false;
    }

    if (controller.isFinalCommand(root, args)) {
      return execute(root, sender, args);
    }

    Optional<Command> commandOpt = controller.getCommand(root, args[0]);
    return commandOpt
        .map(command -> execute(command, sender, controller.deleteFirstArg(args)))
        .orElseGet(() -> execute(root, sender, args));

  }

  protected boolean execute(Command command, CommandSender sender, String[] params) {
    if (sender instanceof ConsoleCommandSender) {
      CommandResult result = command.onConsoleCommand(((ConsoleCommandSender) sender), params);
      return handleResult(command, result, sender);
    }

    if (!(sender instanceof Player)) {
      return false;
    }

    CommandResult result = executePlayerCommand(command, sender, params);
    return handleResult(command, result, sender);
  }

  protected boolean handleResult(Command command, @NotNull CommandResult result,
      CommandSender sender) {
    String[] params = result.getParams();
    switch (result.getType()) {
      case FAILURE:
      case SUCCESS:
        sendParamsMessage(sender, params);
        break;
      case WRONG_SYNTAX:
        sendWrongSyntaxMessage(sender, command);
        break;
      case NO_PERMISSION:
        sendNoPermissionMessage(sender, params);
        break;
      default:
        break;
    }
    return result.getType().asBoolean();
  }

  protected CommandResult executePlayerCommand(@NotNull Command command, CommandSender sender,
      String[] params) {
    Player player = ((Player) sender);
    if (command.getPermission().has(player)) {
      return command.onCommand(player, params);
    }
    return CommandResult.NO_PERMISSIONS;
  }

  private void sendParamsMessage(CommandSender sender, String[] params) {
    if (!Arrays.isEmpty(params)) {
      sender.sendMessage(Colors.color(params));
    }
  }

  private void sendWrongSyntaxMessage(CommandSender sender, Command command) {
    sender.sendMessage(CommonMessage.getSyntaxUsage(command));
  }

  private void sendNoPermissionMessage(CommandSender sender, String[] params) {
    if (Arrays.isEmpty(params) || params.length > 1) {
      sender.sendMessage(CommonMessage.getNoPermissions());
      return;
    }
    sender.sendMessage(CommonMessage.getNoPermission(params[0]));
  }

}
