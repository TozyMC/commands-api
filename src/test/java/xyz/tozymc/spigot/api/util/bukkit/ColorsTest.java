package xyz.tozymc.spigot.api.util.bukkit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.tozymc.spigot.api.util.bukkit.Colors.color;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ColorsTest {

  @Test
  void colorTest_string() {
    String text = "&aPlayer &7joined the game!";

    assertEquals("§aPlayer §7joined the game!", color(text));
  }

  @Test
  void colorTest_array() {
    String[] array = {"&aABC", "&bHello"};
    color(array);
    assertArrayEquals(new String[]{"§aABC", "§bHello"}, array);
  }

  @Test
  void colorTest_list() {
    List<String> list = new ArrayList<>();
    list.add("&aABC");
    list.add("&bHello");

    color(list);
    assertEquals("[§aABC, §bHello]", list.toString());
  }
}