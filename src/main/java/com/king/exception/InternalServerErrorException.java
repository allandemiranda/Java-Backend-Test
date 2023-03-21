package com.king.exception;

/**
 * The type Internal server error exception.
 * The server has encountered a situation it does not know how to handle.
 */
public class InternalServerErrorException extends Exception {

  public InternalServerErrorException(String message) {
    super(message);
  }

  public InternalServerErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalServerErrorException(Throwable cause) {
    super(cause);
  }

  public InternalServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
