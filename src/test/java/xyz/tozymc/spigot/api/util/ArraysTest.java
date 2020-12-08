package xyz.tozymc.spigot.api.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static xyz.tozymc.spigot.api.util.Arrays.isEmpty;
import static xyz.tozymc.spigot.api.util.Arrays.joinToString;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

class ArraysTest {

  @Test
  void joinToStringTest_separator() {
    String[] array = {"One", "Two", "Three"};
    StringJoiner joiner = new StringJoiner("->");

    assertEquals("One->Two->Three", joinToString(joiner, array));
  }

  @Test
  void joinToStringTest_emptyJoiner() {
    String[] array = {"One", "Two", "Three"};
    StringJoiner joiner = new StringJoiner("");

    assertEquals("OneTwoThree", joinToString(joiner, array));
  }

  @Test
  void isEmptyTest_empty() {
    String[] array = {};

    assertTrue(isEmpty(array));
  }

  @Test
  void isEmptyTest_notEmpty() {
    String[] array = {"a"};

    assertFalse(isEmpty(array));
  }

  @Test
  void isEmptyTest_emptyString() {
    String[] array = {"", "", ""};

    assertTrue(isEmpty(array));
  }
}