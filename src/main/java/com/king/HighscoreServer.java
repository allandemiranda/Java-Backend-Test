package com.king;

import com.king.configuration.FileBackedConfig;
import com.king.controller.Middleware;
import com.king.exception.ServerInitializationException;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Highscore server start.
 */
public class HighscoreServer {

  private static final Logger LOGGER = Logger.getLogger(HighscoreServer.class.getSimpleName());

  public static void main(String[] args) {
    try {
      var fileBackedConfig = new FileBackedConfig();
      int port = fileBackedConfig.getPort();
      var addr = new InetSocketAddress(port);
      var httpServer = HttpServer.create(addr, 0);
      httpServer.createContext("/", new Middleware());
      httpServer.start();
      LOGGER.log(Level.INFO, "Server successfully started at port {0}", String.valueOf(port));
    } catch (ServerInitializationException | IOException e) {
      throw new ServerInitializationException("Server could not be started", e);
    }
  }

}
