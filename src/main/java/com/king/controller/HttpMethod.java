package com.king.controller;

import java.util.Arrays;

/**
 * The enum to available Http method.
 */
public enum HttpMethod {
  POST, GET, UNSUPPORTED;

  /**
   * Gets http method.
   *
   * @param value the method name required
   * @return the http method
   */
  public static HttpMethod from(String value) {
    return Arrays.stream(values())
      .filter(httpMethod -> httpMethod.name().equals(value.toUpperCase()))
      .findFirst()
      .orElse(UNSUPPORTED);
  }

}
