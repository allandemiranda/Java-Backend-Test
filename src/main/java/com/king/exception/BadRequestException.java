package com.king.exception;

/**
 * The type Bad request exception.
 * The server cannot or will not process the request due to something that is perceived to be a client error.
 */
public class BadRequestException extends Exception {

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestException(Throwable cause) {
    super(cause);
  }

  public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
