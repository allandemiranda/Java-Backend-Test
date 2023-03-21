package com.king.controller;

import com.king.exception.BadRequestException;
import com.king.exception.MethodNotAllowedException;
import com.king.exception.NotFoundException;
import com.king.exception.NotImplementedException;
import com.king.exception.UnauthorizedException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * The type Middleware to get correct path and inputs.
 */
public class Middleware implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    try {
      var httpRequestModel = HttpRequestParser.parse(exchange);
      var pathParser = HttpPathParser.decodePath(httpRequestModel);
      Router.inject(exchange, httpRequestModel, pathParser);
    } catch (NotImplementedException e) {
      exchange.sendResponseHeaders(501, 0);
      exchange.close();
    } catch (NotFoundException e) {
      exchange.sendResponseHeaders(404, 0);
      exchange.close();
    } catch (BadRequestException e) {
      exchange.sendResponseHeaders(400, 0);
      exchange.close();
    } catch (MethodNotAllowedException e) {
      exchange.sendResponseHeaders(405, 0);
      exchange.close();
    } catch (UnauthorizedException e) {
      exchange.sendResponseHeaders(401, 0);
      exchange.close();
    } catch (Exception e) {
      exchange.sendResponseHeaders(500, 0);
      exchange.close();
    }
  }

}
