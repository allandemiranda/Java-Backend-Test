package com.king.exception;

/**
 * The type Server initialization exception.
 * Error when the http service are starting.
 */
public class ServerInitializationException extends RuntimeException {

  public ServerInitializationException(String message, Exception e) {
    super(message, e);
  }

}
