package xyz.tozymc.spigot.api.command;

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
    rootCommand = new CombinedCommand("root") {
      @NotNull
      @Override
      public CommandResult onCommand(@NotNull CommandSender sender, @NotNull String[] params) {
        return CommandResult.SUCCESS;
      }

      @NotNull
      @Override
      public TabResult onTab(@NotNull CommandSender sender, @NotNull String[] params) {
        return TabResult.empty();
      }

      @NotNull
      @Override
      public PermissionWrapper getPermission() {
        return PermissionWrapper.of("");
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
    childCommand = new CombinedCommand(rootCommand, "child") {
      @NotNull
      @Override
      public CommandResult onCommand(@NotNull CommandSender sender, @NotNull String[] params) {
        return CommandResult.SUCCESS;
      }

      @NotNull
      @Override
      public TabResult onTab(@NotNull CommandSender sender, @NotNull String[] params) {
        return TabResult.empty();
      }

      @NotNull
      @Override
      public PermissionWrapper getPermission() {
        return PermissionWrapper.of("");
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
    Assertions.assertEquals("Command(name=root)", rootCommand.toString());
  }

  @Test
  void toStringTest_child() {
    Assertions.assertEquals("Command(root=root,name=child)", childCommand.toString());
  }


}