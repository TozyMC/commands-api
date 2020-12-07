package xyz.tozymc.spigot.api.command;

import static xyz.tozymc.spigot.api.command.result.CommandResult.SUCCESS;
import static xyz.tozymc.spigot.api.command.result.TabResult.empty;
import static xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper.of;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.tozymc.spigot.api.command.result.CommandResult;
import xyz.tozymc.spigot.api.command.result.TabResult;
import xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper;

class AbstractCommandTest {

  private Command rootCommand;
  private Command childCommand;

  @BeforeEach
  void setUp() {
    rootCommand = new RootCommand();

    childCommand = new CombinedCommand(rootCommand, "child") {
      @NotNull
      @Override
      public CommandResult onCommand(@NotNull CommandSender sender, @NotNull String[] params) {
        return SUCCESS;
      }

      @NotNull
      @Override
      public TabResult onTab(@NotNull CommandSender sender, @NotNull String[] params) {
        return empty();
      }

      @NotNull
      @Override
      public PermissionWrapper getPermission() {
        return of("");
      }

      @NotNull
      @Override
      public String getSyntax() {
        return "";
      }

      @NotNull
      @Override
      public String getDescription() {
        return "";
      }
    };
  }

  @Test
  void toStringTest_root() {
    Assertions.assertEquals("RootCommand(name=root)", rootCommand.toString());
  }

  @Test
  void toStringTest_child() {
    Assertions.assertEquals("(root=root,name=child)", childCommand.toString());
  }

  private static class RootCommand extends CombinedCommand {

    private RootCommand() {
      super("root");
    }

    @NotNull
    @Override
    public CommandResult onCommand(@NotNull CommandSender sender, @NotNull String[] params) {
      return SUCCESS;
    }

    @NotNull
    @Override
    public TabResult onTab(@NotNull CommandSender sender, @NotNull String[] params) {
      return empty();
    }

    @NotNull
    @Override
    public PermissionWrapper getPermission() {
      return of("");
    }

    @NotNull
    @Override
    public String getSyntax() {
      return "";
    }

    @NotNull
    @Override
    public String getDescription() {
      return "";
    }
  }
}