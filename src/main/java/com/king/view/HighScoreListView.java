package com.king.view;

import com.king.configuration.DataBase;
import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * The type High score list view.
 */
public final class HighScoreListView implements ViewMethod {

  public static final String REPLACEMENT = "";
  public static final String TARGET_OPEN = "{";
  public static final String TARGET_CLOSE = "}";

  @Override
  public void get(HttpExchange exchange, Object[] data) throws InternalServerErrorException, BadRequestException {
    if (Objects.isNull(exchange) || Objects.isNull(data) || data.length != 1 || Objects.isNull(data[0])) {
      throw new InputMismatchException("Unexpected input value");
    }
    if (Math.abs(((int) data[0])) != ((int) data[0])) {
      throw new BadRequestException("Unexpected a negative number");
    }
    try (OutputStream outputStream = exchange.getResponseBody()) {
      var response = DataBase.getLevelData((int) data[0]).toString()
        .replace(TARGET_OPEN, REPLACEMENT)
        .replace(TARGET_CLOSE, REPLACEMENT);
      exchange.sendResponseHeaders(200, response.length());
      outputStream.write(response.getBytes());
      exchange.close();
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage());
    }
  }
}
