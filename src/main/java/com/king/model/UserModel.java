package com.king.model;

import java.util.InputMismatchException;
import java.util.Objects;

/**
 * The type User model.
 */
public record UserModel(int id) {
  public UserModel {
    if (Math.abs(id) != id) {
      throw new InputMismatchException("Unexpected a negative number");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserModel userModel = (UserModel) o;
    return id == userModel.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
