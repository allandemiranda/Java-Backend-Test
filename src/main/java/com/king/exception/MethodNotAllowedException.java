package com.king.exception;

/**
 * The type Method not allowed exception.
 * The request method is known by the server but is not supported by the target resource.
 */
public class MethodNotAllowedException extends Exception {

  public MethodNotAllowedException(String message) {
    super(message);
  }

  public MethodNotAllowedException(String message, Throwable cause) {
    super(message, cause);
  }

  public MethodNotAllowedException(Throwable cause) {
    super(cause);
  }

  public MethodNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
