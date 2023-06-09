package com.king.exception;

/**
 * The type Unauthorized exception.
 * Although the HTTP standard specifies "unauthorized", semantically this response means "unauthenticated".
 */
public class UnauthorizedException extends Exception {

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnauthorizedException(Throwable cause) {
    super(cause);
  }

  public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
