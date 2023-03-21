package com.king.controller;

import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.exception.MethodNotAllowedException;
import com.king.exception.NotFoundException;
import com.king.exception.NotImplementedException;
import com.king.exception.UnauthorizedException;
import com.king.model.HttpRequestModel;
import com.king.view.HighScoreListView;
import com.king.view.LoginView;
import com.king.view.ScoreView;
import com.king.view.ViewMethod;
import com.sun.net.httpserver.HttpExchange;

import java.util.AbstractMap;

/**
 * The type Router.
 */
public final class Router {

  public static final String MSG_METHOD_UNSUPPORTED = "Method unsupported";

  private Router() {
    throw new IllegalStateException("Router class");
  }

  /**
   * Router map
   *
   * @param exchange         the exchange
   * @param httpRequestModel the http request model
   * @param pathParser       the path parser
   * @throws NotImplementedException      the not implemented exception
   * @throws InternalServerErrorException the internal server error exception
   * @throws MethodNotAllowedException    the method not allowed exception
   * @throws UnauthorizedException        the unauthorized exception
   * @throws BadRequestException          the bad request exception
   * @throws NotFoundException            the not found exception
   */
  public static void inject(HttpExchange exchange, HttpRequestModel httpRequestModel, AbstractMap.SimpleEntry<Integer, HttpPath> pathParser) throws NotImplementedException, InternalServerErrorException, MethodNotAllowedException, UnauthorizedException, BadRequestException, NotFoundException {
    switch (pathParser.getValue()) {
      case LOGIN -> methodProxy(httpRequestModel, new LoginView(), exchange, new Object[]{pathParser.getKey()});
      case SCORE -> methodProxy(httpRequestModel, new ScoreView(), exchange, new Object[]{pathParser.getKey(), httpRequestModel});
      case HIGHSCORELIST -> methodProxy(httpRequestModel, new HighScoreListView(), exchange, new Object[]{pathParser.getKey()});
      default -> throw new NotFoundException(pathParser.getValue().toString());
    }
  }

  /**
   * Method router.
   *
   * @param httpRequestModel the http request model
   * @param aClass           the router pathway class
   * @param exchange         the http exchange
   * @param data             the inputs and parameters
   * @throws MethodNotAllowedException    the method not allowed exception
   * @throws UnauthorizedException        the unauthorized exception
   * @throws BadRequestException          the bad request exception
   * @throws InternalServerErrorException the internal server error exception
   * @throws NotImplementedException      the not implemented exception
   */
  private static void methodProxy(HttpRequestModel httpRequestModel, ViewMethod aClass, HttpExchange exchange, Object[] data) throws MethodNotAllowedException, UnauthorizedException, BadRequestException, InternalServerErrorException, NotImplementedException {
    switch (httpRequestModel.httpMethod()) {
      case GET -> aClass.get(exchange, data);
      case POST -> aClass.post(exchange, data);
      case UNSUPPORTED -> throw new NotImplementedException(MSG_METHOD_UNSUPPORTED);
    }
  }
}
