package com.king.controller;

import com.king.exception.InternalServerErrorException;
import com.king.model.HttpRequestModel;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The type Http request parser.
 */
public final class HttpRequestParser {

  private static final Pattern QUERY_PARAMETERS_PATTERN = Pattern.compile("([^&=]+)=([^&=]+)");

  private HttpRequestParser() {
    throw new IllegalStateException("HttpRequestParser class");
  }

  /**
   * Parse http request model.
   *
   * @param exchange the exchange
   * @return the http request model
   * @throws InternalServerErrorException the internal server error exception
   */
  public static HttpRequestModel parse(HttpExchange exchange) throws InternalServerErrorException {
    try {
      var body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
      var path = exchange.getRequestURI().getPath();
      var httpMethod = HttpMethod.from(exchange.getRequestMethod());
      var parameters = getParameters(exchange);
      return new HttpRequestModel(httpMethod, path, body, parameters);
    } catch (IOException e) {
      throw new InternalServerErrorException("The provided request could not be parsed");
    }
  }

  /**
   * Gets parameters from url.
   *
   * @param exchange the http exchange required
   * @return the parameters
   */
  private static Map<String, String> getParameters(HttpExchange exchange) {
    var query = exchange.getRequestURI().getQuery();
    return Objects.isNull(query) ? Collections.emptyMap() :
      QUERY_PARAMETERS_PATTERN.matcher(query).results()
        .collect(Collectors.toMap(
          matchResult -> matchResult.group(1),
          matchResult -> matchResult.group(2),
          (o, o2) -> o2,
          HashMap::new));
  }

}
