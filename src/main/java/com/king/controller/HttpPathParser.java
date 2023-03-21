package com.king.controller;

import com.king.exception.BadRequestException;
import com.king.exception.NotFoundException;
import com.king.model.HttpRequestModel;

import java.util.AbstractMap;

/**
 * The type Http path parser.
 */
public final class HttpPathParser {

  private HttpPathParser() {
    throw new IllegalStateException("LoginViewViewMethod class");
  }

  /**
   * Decode path to number and path
   *
   * @param httpRequestModel the http request model
   * @return the pair number and path
   * @throws NotFoundException   the not found exception
   * @throws BadRequestException the bad request exception
   */
  public static AbstractMap.SimpleEntry<Integer, HttpPath> decodePath(HttpRequestModel httpRequestModel) throws NotFoundException, BadRequestException {
    var path = HttpPath.path(httpRequestModel.path());
    if (HttpPath.NOT_FOUND.equals(path)) {
      throw new NotFoundException("Not found path: ".concat(httpRequestModel.path()));
    } else {
      try {
        var number = Integer.valueOf(httpRequestModel.path().split("/")[1]);
        return new AbstractMap.SimpleEntry<>(number, path);
      } catch (Exception e) {
        throw new BadRequestException("Can't decode Path: ".concat(e.getMessage()));
      }
    }
  }
}
