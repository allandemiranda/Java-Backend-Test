package com.king.configuration;

import java.time.Duration;

/**
 * The interface Config.
 */
public interface Config {

  /**
   * Gets port to run the http server.
   *
   * @return the port number
   */
  int getPort();

  /**
   * Gets session time to live the User.
   *
   * @return the duration to session time
   */
  Duration getSessionTimeToLive();

}
