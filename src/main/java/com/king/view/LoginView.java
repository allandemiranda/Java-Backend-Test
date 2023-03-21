package com.king.view;

import com.king.controller.SessionKey;
import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.model.UserModel;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * The type Login view.
 */
public final class LoginView implements ViewMethod {

  @Override
  public void get(HttpExchange exchange, Object[] data) throws InternalServerErrorException, BadRequestException {
    if (Objects.isNull(exchange) || Objects.isNull(data) || data.length != 1 || Objects.isNull(data[0])) {
      throw new InputMismatchException("Unexpected input value");
    }
    try (OutputStream outputStream = exchange.getResponseBody()) {
      var userModel = new UserModel((int) data[0]);
      var response = SessionKey.getKey(userModel);
      exchange.sendResponseHeaders(200, response.length());
      outputStream.write(response.getBytes());
      exchange.close();
    } catch (InputMismatchException e) {
      throw new BadRequestException(e.getMessage());
    } catch (Exception e) {
      throw new InternalServerErrorException(e.getMessage());
    }
  }
}
