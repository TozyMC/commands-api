package xyz.tozymc.spigot.api.command.handler;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.command.Command;
import xyz.tozymc.spigot.api.command.CommandController;
import xyz.tozymc.spigot.api.command.result.TabResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TabHandler implements TabCompleter {

  private final CommandController controller;

  public TabHandler(CommandController controller) {
    this.controller = controller;
  }

  @Override
  @Nullable
  public List<String> onTabComplete(@NotNull CommandSender sender,
      @NotNull org.bukkit.command.Command bukkitCmd,
      @NotNull String alias,
      @NotNull String[] args) {

    Command root = controller.getRootCommand(bukkitCmd.getName());
    if (root == null) {
      return null;
    }

    if (controller.isFinalCommand(root, args)) {
      List<String> completer = executeTabComplete(root, sender, args);
      if (completer.isEmpty() && args.length == 1) {
        return StringUtil
            .copyPartialMatches(args[0], getChildCommandNames(root), new ArrayList<>());
      }
      return completer;
    }

    if (args.length == 1) {
      return StringUtil
          .copyPartialMatches(args[0], getChildCommandNames(root), new ArrayList<>());
    }

    Optional<Command> commandOpt = controller.getCommand(root, args[0]);
    return commandOpt
        .map(command -> executeTabComplete(command, sender, controller.deleteFirstArg(args)))
        .orElse(executeTabComplete(root, sender, args));
  }

  private List<String> executeTabComplete(Command command, CommandSender sender, String[] params) {
    if (sender instanceof ConsoleCommandSender) {
      TabResult result = command.onConsoleTab(((ConsoleCommandSender) sender), params);
      return result.getResult();
    }
    TabResult result = onPlayerTab(sender, command, params);
    return result.getResult();
  }

  private TabResult onPlayerTab(CommandSender sender, @NotNull Command command, String[] params) {
    Player player = (Player) sender;
    return command.getPermission().has(player) ? command.onTab(player, params) : TabResult.empty();
  }

  private Iterable<String> getChildCommandNames(Command root) {
    List<Command> children = controller.getCommands().get(root);
    if (children == null) {
      return new ArrayList<>();
    }
    return children.stream().map(Command::getName)
        .collect(Collectors.toList());
  }
}
