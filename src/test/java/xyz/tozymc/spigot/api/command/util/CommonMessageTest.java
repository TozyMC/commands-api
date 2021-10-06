package xyz.tozymc.spigot.api.command.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static xyz.tozymc.spigot.api.command.result.CommandResult.SUCCESS;
import static xyz.tozymc.spigot.api.command.result.TabResult.empty;
import static xyz.tozymc.spigot.api.command.util.CommonMessage.getNoPermission;
import static xyz.tozymc.spigot.api.command.util.CommonMessage.getSyntaxUsage;
import static xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper.of;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.tozymc.spigot.api.command.CombinedCommand;
import xyz.tozymc.spigot.api.command.Command;
import xyz.tozymc.spigot.api.command.result.CommandResult;
import xyz.tozymc.spigot.api.command.result.TabResult;
import xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper;

class CommonMessageTest {

  private Command testCommand;

  @BeforeEach
  void setUp() {
    testCommand = new TestCommand();
  }

  @Test
  void getPermissionTest_withPermission() {
    assertArrayEquals(new String[]{"\u00A7cYou don't have permission test to do that."},
        getNoPermission("test")); // "§cYou don't have permission test to do that."
  }

  @Test
  void getSyntaxUsageTest() {
    assertArrayEquals(new String[]{"\u00A7cUsage: \u00A7f/test \u00A77- Test command"},
        getSyntaxUsage(testCommand)); // "§cUsage: §f/test §7- Test command"
  }

  @AfterEach
  void tearDown() {
    testCommand = null;
  }

  private static class TestCommand extends CombinedCommand {

    private TestCommand() {
      super("test");
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
      return of("test");
    }

    @NotNull
    @Override
    public String getSyntax() {
      return "/test";
    }

    @NotNull
    @Override
    public String getDescription() {
      return "Test command";
    }
  }
}