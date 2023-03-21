package com.king.controller;

import com.king.configuration.FileBackedConfig;
import com.king.exception.BadRequestException;
import com.king.exception.UnauthorizedException;
import com.king.model.UserModel;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.InputMismatchException;

/**
 * The class to solver key User logic.
 */
public final class SessionKey {

  private static final String EMPTY_KEY = "";
  private static final int SALT = 5;
  private static final String SPLIT_KEY = "/";

  private SessionKey() {
    throw new IllegalStateException("SessionKey class");
  }

  /**
   * Gets key.
   *
   * @param userModel the user
   * @return the key
   */
  public static String getKey(UserModel userModel) {
    var localDateTime = String.valueOf(LocalDateTime.now());
    var userNum = String.valueOf(userModel.id());
    var keyNotScripted = userNum.concat("/").concat(localDateTime);
    var keyPlusSalt = Arrays.stream(keyNotScripted.chars().toArray()).boxed()
      .map(i -> ((char) (i + SALT)))
      .map(Object::toString)
      .reduce(EMPTY_KEY, String::concat);
    return Base64.getUrlEncoder().encodeToString(keyPlusSalt.getBytes());
  }

  /**
   * Gets user by key
   *
   * @param key the key
   * @return the user
   * @throws UnauthorizedException the unauthorized exception
   * @throws BadRequestException   the bad request exception
   */
  public static UserModel getUser(String key) throws UnauthorizedException, BadRequestException {
    var decodedBytes = Base64.getUrlDecoder().decode(key);
    var decodedKeyOld = new String(decodedBytes);
    var decodedKey = Arrays.stream(decodedKeyOld.chars().toArray()).boxed().map(i -> ((char) (i - SALT))).map(Object::toString).reduce(EMPTY_KEY, String::concat);
    if (timeValidation(decodedKey)) {
      try {
        return new UserModel(Integer.parseInt(decodedKey.split(SPLIT_KEY)[0]));
      } catch (InputMismatchException e) {
        throw new BadRequestException(e.getMessage());
      }
    } else {
      throw new UnauthorizedException("Out of Time");
    }
  }

  /**
   * Time validation boolean.
   *
   * @param key the key
   * @return the boolean
   */
  private static boolean timeValidation(String key) {
    try {
      var values = key.split(SPLIT_KEY);
      var minutes = new FileBackedConfig().getSessionTimeToLive().toMinutes();
      var localDateTime = LocalDateTime.parse(values[1]).plusMinutes(minutes);
      return values.length == 2 && LocalDateTime.now().isBefore(localDateTime);
    } catch (Exception e) {
      return false;
    }
  }
}
