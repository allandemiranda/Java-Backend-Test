package com.king.controller;

import java.util.Arrays;

/**
 * The enum available Http Path.
 */
public enum HttpPath {
  LOGIN, SCORE, HIGHSCORELIST, NOT_FOUND;

  /**
   * Gets http path value.
   *
   * @param path the path
   * @return the http path
   */
  public static HttpPath path(String path) {
    String[] data = path.split("/");
    return (data.length == 3 &&
      !data[1].isEmpty() &&
      !data[2].isEmpty() &&
      Arrays.stream(data[1].split(""))
        .noneMatch(s -> s.toCharArray()[0] > 57 || s.toCharArray()[0] < 48)
    ) ?
      Arrays.stream(values())
        .filter(httpMethod -> httpMethod.name().toLowerCase().equals(data[2]))
        .findFirst()
        .orElse(NOT_FOUND) :
      NOT_FOUND;
  }
}
