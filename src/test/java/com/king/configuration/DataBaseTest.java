package com.king.configuration;

import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class DataBaseTest {

  @Test
  @Order(1)
  void getLevelDataOrder() throws BadRequestException, InternalServerErrorException {
    //given
    int level1 = 1;
    int level2 = 2;
    DataBase.addScore(new UserModel(10), level1, 100);
    DataBase.addScore(new UserModel(20), level1, 300);

    DataBase.addScore(new UserModel(20), level2, 2);

    DataBase.addScore(new UserModel(30), level1, 200);
    DataBase.addScore(new UserModel(1), level1, 1);
    //when
    String result1 = String.valueOf(DataBase.getLevelData(level1));
    String result2 = String.valueOf(DataBase.getLevelData(level2));
    //then
    Assertions.assertEquals("{20=300, 30=200, 10=100, 1=1}", result1);
    Assertions.assertEquals("{20=2}", result2);
  }

  @Test
  @Order(2)
  void getLevelDataSize() {
    //given
    IntStream.range(50, 100).parallel().forEach(i -> {
      try {
        DataBase.addScore(new UserModel(i), 1, i * 3);
      } catch (BadRequestException | InternalServerErrorException e) {
        throw new RuntimeException(e);
      }
    });
    //when
    //then
    Assertions.assertEquals(15, DataBase.getLevelData(1).size());
  }

  @Test
  void addScore() {
    //given
    UserModel userModel = Mockito.mock(UserModel.class);
    int level = 1;
    int points = 1;
    //when
    Executable executable = () -> DataBase.addScore(userModel, level, points);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void addScoreNull() {
    //given
    UserModel userModel = null;
    int level = 1;
    int points = 1;
    //when
    Executable executable = () -> DataBase.addScore(userModel, level, points);
    //then
    Assertions.assertThrows(InternalServerErrorException.class, executable);
  }

  @Test
  void addScoreNVal() {
    //given
    UserModel userModel = Mockito.mock(UserModel.class);
    int level = -1;
    int points = -1;
    //when
    Executable executable1 = () -> DataBase.addScore(userModel, level, 1);
    Executable executable2 = () -> DataBase.addScore(userModel, 1, points);
    Executable executable3 = () -> DataBase.addScore(userModel, level, points);
    //then
    Assertions.assertThrows(BadRequestException.class, executable1);
    Assertions.assertThrows(BadRequestException.class, executable2);
    Assertions.assertThrows(BadRequestException.class, executable3);
  }

  @Test
  void getLevelData() {
    //given
    int level = 1;
    //when
    Executable executable = () -> DataBase.getLevelData(level);
    //then
    Assertions.assertDoesNotThrow(executable);
  }
}