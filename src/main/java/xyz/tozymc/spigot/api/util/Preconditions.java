/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package xyz.tozymc.spigot.api.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Static convenience methods that help a method or constructor check whether it was invoked
 * correctly (whether its <i>preconditions</i> have been met). These methods generally accept a
 * {@code boolean} expression which is expected to be {@code true} (or in the case of {@code
 * checkNotNull}, an object reference which is expected to be non-null). When {@code false} (or
 * {@code null}) is passed instead, the {@code Preconditions} method throws an unchecked exception,
 * which helps the calling method communicate to <i>its</i> caller that <i>that</i> caller has made
 * a mistake.
 *
 * @author Kevin Bourrillion
 * @since 2.0
 */
public final class Preconditions {

  private Preconditions() {}

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression   a boolean expression
   * @param errorMessage the exception message to use if the check fails; will be converted to a
   *                     string using {@link String#valueOf(Object)}
   * @throws IllegalStateException if {@code expression} is false
   */
  public static void checkState(boolean expression, @Nullable Object errorMessage) {
    if (!expression) {
      throw new IllegalStateException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression           a boolean expression
   * @param errorMessageTemplate a template for the exception message should the check fail. The
   *                             message is formed by replacing each {@code %s} placeholder in the
   *                             template with an argument. These are matched by position - the
   *                             first {@code %s} gets {@code errorMessageArgs[0]}, etc. Unmatched
   *                             arguments will be appended to the formatted message in square
   *                             braces. Unmatched placeholders will be left as-is.
   * @param errorMessageArgs     the arguments to be substituted into the message template.
   *                             Arguments are converted to strings using {@link
   *                             String#valueOf(Object)}.
   * @throws IllegalStateException if {@code expression} is false
   * @throws NullPointerException  if the check fails and either {@code errorMessageTemplate} or
   *                               {@code errorMessageArgs} is null (don't let this happen)
   */
  public static void checkState(boolean expression, @Nullable String errorMessageTemplate,
      @Nullable Object... errorMessageArgs) {
    if (!expression) {
      throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   */
  @NotNull
  @Contract(value = "null -> fail; !null -> param1", pure = true)
  public static <T> T checkNotNull(T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }

  static String format(String template, @Nullable Object... args) {
    template = String.valueOf(template);

    // start substituting the arguments into the '%s' placeholders
    if (args == null) {
      return template;
    }
    StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
    int templateStart = 0;
    int i = 0;
    while (i < args.length) {
      int placeholderStart = template.indexOf("%s", templateStart);
      if (placeholderStart == -1) {
        break;
      }
      builder.append(template, templateStart, placeholderStart);
      builder.append(args[i++]);
      templateStart = placeholderStart + 2;
    }
    builder.append(template, templateStart, template.length());

    // if we run out of placeholders, append the extra args in square braces
    if (i < args.length) {
      builder.append(" [");
      builder.append(args[i++]);
      while (i < args.length) {
        builder.append(", ");
        builder.append(args[i++]);
      }
      builder.append(']');
    }

    return builder.toString();
  }
}
