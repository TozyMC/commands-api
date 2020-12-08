package xyz.tozymc.spigot.api.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.tozymc.spigot.api.util.Lists.addAll;
import static xyz.tozymc.spigot.api.util.Lists.newArrayList;
import static xyz.tozymc.spigot.api.util.Lists.setAll;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ListsTest {

  @Test
  void newArrayListTest_notEmpty() {
    assertEquals("[a, b, 1, 2]", newArrayList("a", "b", 1, 2).toString());
  }

  @Test
  void newArrayListTest_empty() {
    assertEquals("[]", newArrayList().toString());
  }

  @Test
  void addAllTest_notEmpty() {
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("a");
    arrayList.add("b");

    addAll(arrayList, "d", "c");
    assertEquals("[a, b, d, c]", arrayList.toString());
  }

  @Test
  void addAllTest_addEmpty() {
    ArrayList<String> arrayList = new ArrayList<>();
    addAll(arrayList);
    assertEquals("[]", arrayList.toString());
  }

  @Test
  void addAllTest_empty() {
    ArrayList<String> arrayList = new ArrayList<>();
    addAll(arrayList, "a", "b");
    assertEquals("[a, b]", arrayList.toString());
  }

  @Test
  void setAllTest_replace() {
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("a");
    arrayList.add("b");
    arrayList.add("c");
    arrayList.add("a");

    setAll(arrayList, s -> {
      if (s.equals("a")) {
        return "|";
      }
      return s;
    });

    assertEquals("[|, b, c, |]", arrayList.toString());
  }

  @Test
  void setAllTest_set() {
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("a");
    arrayList.add("b");
    arrayList.add("c");
    arrayList.add("a");

    setAll(arrayList, s -> "s");

    assertEquals("[s, s, s, s]", arrayList.toString());
  }

  @Test
  void setAllTest_null() {
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("a");
    arrayList.add("b");
    arrayList.add("c");
    arrayList.add("a");

    setAll(arrayList, null);

    assertEquals("[a, b, c, a]", arrayList.toString());
  }
}