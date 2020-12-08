package xyz.tozymc.spigot.api.util.bukkit.permission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper.of;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PermissionWrapperTest {

  private PermissionWrapper emptyPermission;
  private PermissionWrapper opPermission;
  private PermissionWrapper testPermission;
  private PermissionWrapper likeOpPermission;

  @BeforeEach
  void setUp() {
    emptyPermission = of("");
    opPermission = of("op");
    likeOpPermission = of("op");
    testPermission = of(opPermission, "test");
  }

  @Test
  void getPermissionTest_op() {
    assertEquals("op", opPermission.getPermission());
  }

  @Test
  void getPermissionTest_test() {
    assertEquals("op.test", testPermission.getPermission());
  }

  @Test
  void getPermissionTest_empty() {
    assertEquals("", emptyPermission.getPermission());
  }

  @Test
  void equalsTest_equals() {
    assertEquals(likeOpPermission, opPermission);
  }

  @Test
  void equalsTest_notEquals() {
    assertNotEquals(opPermission, testPermission);
  }

  @AfterEach
  void tearDown() {
    emptyPermission = null;
    opPermission = null;
    likeOpPermission = null;
    testPermission = null;
  }
}