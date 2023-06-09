package com.king.exception;

/**
 * The type Not implemented exception.
 * The request method is not supported by the server and cannot be handled.
 */
public class NotImplementedException extends Exception {

  public NotImplementedException(String message) {
    super(message);
  }

  public NotImplementedException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotImplementedException(Throwable cause) {
    super(cause);
  }

  public NotImplementedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
