package com.king.model;

import java.util.InputMismatchException;
import java.util.Objects;

/**
 * The type Score model.
 */
public record ScoreModel(UserModel userModel, int level, int score) {

  public ScoreModel {
    Objects.requireNonNull(userModel);
    if (Math.abs(level) != level || Math.abs(score) != score) {
      throw new InputMismatchException("Unexpected a negative number");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ScoreModel scoreModel = (ScoreModel) o;
    return level == scoreModel.level && Objects.equals(userModel, scoreModel.userModel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userModel, level);
  }
}
