package com.king.view;

import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.exception.MethodNotAllowedException;
import com.king.exception.UnauthorizedException;
import com.sun.net.httpserver.HttpExchange;

/**
 * The interface View method.
 */
public interface ViewMethod {

  /**
   * Get method
   *
   * @param exchange the exchange
   * @param data     the data
   * @throws MethodNotAllowedException    the method not allowed exception
   * @throws InternalServerErrorException the internal server error exception
   * @throws BadRequestException          the bad request exception
   */
  default void get(HttpExchange exchange, Object[] data) throws MethodNotAllowedException, InternalServerErrorException, BadRequestException {
    throw new MethodNotAllowedException("The request method GET is known by the server but is not supported by the target resource");
  }

  /**
   * Post method
   *
   * @param exchange the exchange
   * @param data     the data
   * @throws UnauthorizedException        the unauthorized exception
   * @throws BadRequestException          the bad request exception
   * @throws InternalServerErrorException the internal server error exception
   * @throws MethodNotAllowedException    the method not allowed exception
   */
  default void post(HttpExchange exchange, Object[] data) throws UnauthorizedException, BadRequestException, InternalServerErrorException, MethodNotAllowedException {
    throw new MethodNotAllowedException("The request method POST is known by the server but is not supported by the target resource");
  }

  /**
   * Put method
   *
   * @param exchange the exchange
   * @param data     the data
   * @throws MethodNotAllowedException the method not allowed exception
   */
  default void put(HttpExchange exchange, Object[] data) throws MethodNotAllowedException {
    throw new MethodNotAllowedException("The request method PUT is known by the server but is not supported by the target resource");
  }

  /**
   * Delete method
   *
   * @param exchange the exchange
   * @param data     the data
   * @throws MethodNotAllowedException the method not allowed exception
   */
  default void delete(HttpExchange exchange, Object[] data) throws MethodNotAllowedException {
    throw new MethodNotAllowedException("The request method DELETE is known by the server but is not supported by the target resource");
  }
}
