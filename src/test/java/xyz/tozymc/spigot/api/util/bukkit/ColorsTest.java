package xyz.tozymc.spigot.api.util.bukkit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.tozymc.spigot.api.util.bukkit.Colors.color;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ColorsTest {

  @Test
  void colorTest_string() {
    String text = "&aPlayer &7joined the game!";

    assertEquals("\u00A7aPlayer \u00A77joined the game!", color(text)); // "§aPlayer §7joined the game!"
  }

  @Test
  void colorTest_array() {
    String[] array = {"&aABC", "&bHello"};
    color(array);
    assertArrayEquals(new String[]{"\u00A7aABC", "\u00A7bHello"}, array); // {"§aABC", "§bHello"}
  }

  @Test
  void colorTest_list() {
    List<String> list = new ArrayList<>();
    list.add("&aABC");
    list.add("&bHello");

    color(list);
    assertEquals("[\u00A7aABC, \u00A7bHello]", list.toString()); // "[§aABC, §bHello]"
  }
}