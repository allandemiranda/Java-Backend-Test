package com.king.configuration;

import com.king.exception.ServerInitializationException;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * Api configurations.
 */
public final class FileBackedConfig implements Config {

  private static final String CONFIG_FILE = "config.properties";
  private static final String PORT_PROPERTY = "highscore.port";
  private static final String SESSION_TTL_PROPERTY = "highscore.sessionTimeToLive";

  private final Properties properties;

  public FileBackedConfig() {
    properties = new Properties();
    try (var input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
      properties.load(input);
    } catch (IOException e) {
      throw new ServerInitializationException("Configuration file not found", e);
    }
  }

  @Override
  public int getPort() {
    try {
      return Integer.parseInt(properties.getProperty(PORT_PROPERTY));
    } catch (Exception e) {
      throw new ServerInitializationException(e.getMessage(), e);
    }
  }

  @Override
  public Duration getSessionTimeToLive() {
    try {
      return Duration.parse(properties.getProperty(SESSION_TTL_PROPERTY));
    } catch (Exception e) {
      throw new ServerInitializationException(e.getMessage(), e);
    }
  }

}
