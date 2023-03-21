package com.king.view;

import com.king.configuration.DataBase;
import com.king.controller.SessionKey;
import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.exception.UnauthorizedException;
import com.king.model.HttpRequestModel;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * The type Score view.
 */
public final class ScoreView implements ViewMethod {

  public static final String SESSIONKEY = "sessionkey";

  @Override
  public void post(HttpExchange exchange, Object[] data) throws UnauthorizedException, BadRequestException, InternalServerErrorException {
    if (Objects.isNull(exchange) || Objects.isNull(data) || data.length != 2 || Objects.isNull(data[0]) || Objects.isNull(data[1])) {
      throw new InputMismatchException("Unexpected input value");
    }
    HttpRequestModel requestModel = (HttpRequestModel) data[1];
    if (requestModel.parameters().containsKey(SESSIONKEY)) {
      var userModel = SessionKey.getUser(requestModel.parameters().get(SESSIONKEY));
      try {
        int level = (int) data[0];
        DataBase.addScore(userModel, level, Integer.parseInt(requestModel.body()));
        exchange.sendResponseHeaders(201, 0);
        exchange.close();
      } catch (NumberFormatException e) {
        throw new BadRequestException("Body score value not is a valid number");
      } catch (IOException e) {
        throw new InternalServerErrorException(e.getMessage());
      }
    } else {
      throw new BadRequestException("Parameter 'sessionkey' not fond");
    }
  }
}
