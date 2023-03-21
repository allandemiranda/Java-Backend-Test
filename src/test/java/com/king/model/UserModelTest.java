package com.king.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.InputMismatchException;

@ExtendWith(MockitoExtension.class)
class UserModelTest {

  @Test
  void testEquals() {
    //given
    int id1 = 1;
    int id2 = 2;
    //when
    UserModel userModel1 = new UserModel(id1);
    UserModel userModel2 = new UserModel(id2);
    //then
    Assertions.assertEquals(userModel1, userModel1);
    Assertions.assertNotEquals(userModel1, userModel2);
  }

  @Test
  void testHashCode() {
    //given
    int id1 = 1;
    int id2 = 2;
    //when
    UserModel userModel1 = new UserModel(id1);
    UserModel userModel2 = new UserModel(id2);
    //then
    Assertions.assertEquals(userModel1.hashCode(), userModel1.hashCode());
    Assertions.assertNotEquals(userModel1.hashCode(), userModel2.hashCode());
  }

  @Test
  void id() {
    //given
    int id = 1;
    int badId = -1;
    //when
    Executable executable = () -> new UserModel(id);
    Executable badexecutable = () -> new UserModel(badId);
    //then
    Assertions.assertDoesNotThrow(executable);
    Assertions.assertThrows(InputMismatchException.class, badexecutable);
    Assertions.assertEquals(id, (new UserModel(id)).id());
  }

}