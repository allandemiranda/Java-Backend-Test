package com.king.model;

import com.king.controller.HttpMethod;

import java.util.Map;
import java.util.Objects;

/**
 * The type Http request model.
 */
public record HttpRequestModel(HttpMethod httpMethod, String path, String body, Map<String, String> parameters) {
  public HttpRequestModel {
    Objects.requireNonNull(httpMethod);
    Objects.requireNonNull(path);
    Objects.requireNonNull(body);
    Objects.requireNonNull(parameters);
  }
}
