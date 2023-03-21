package com.king.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.InputMismatchException;

@ExtendWith(MockitoExtension.class)
class ScoreModelTest {

  @Test
  void testEquals() {
    //given
    UserModel userModel1 = Mockito.mock(UserModel.class);
    UserModel userModel2 = Mockito.mock(UserModel.class);
    int level1 = 0;
    int level2 = 1;
    int score1 = 0;
    int score2 = 1;
    //when
    ScoreModel scoreModel1 = new ScoreModel(userModel1, level1, score1);
    ScoreModel scoreModel2 = new ScoreModel(userModel1, level1, score2);

    ScoreModel scoreModel3 = new ScoreModel(userModel1, level2, score1);
    ScoreModel scoreModel4 = new ScoreModel(userModel2, level1, score1);

    ScoreModel scoreModel5 = new ScoreModel(userModel1, level2, score1);
    ScoreModel scoreModel6 = new ScoreModel(userModel2, level2, score1);

    ScoreModel scoreModel7 = new ScoreModel(userModel2, level2, score1);
    ScoreModel scoreModel8 = new ScoreModel(userModel2, level1, score1);
    //then
    Assertions.assertEquals(scoreModel1, scoreModel1);
    Assertions.assertEquals(scoreModel1, scoreModel2);
    Assertions.assertNotEquals(scoreModel3, scoreModel4);
    Assertions.assertNotEquals(scoreModel5, scoreModel6);
    Assertions.assertNotEquals(scoreModel7, scoreModel8);
  }

  @Test
  void testHashCode() {
    //given
    UserModel userModel1 = Mockito.mock(UserModel.class);
    UserModel userModel2 = Mockito.mock(UserModel.class);
    int level1 = 0;
    int level2 = 1;
    int score1 = 0;
    int score2 = 1;
    //when
    ScoreModel scoreModel1 = new ScoreModel(userModel1, level1, score1);
    ScoreModel scoreModel2 = new ScoreModel(userModel1, level1, score2);

    ScoreModel scoreModel3 = new ScoreModel(userModel1, level2, score1);
    ScoreModel scoreModel4 = new ScoreModel(userModel2, level1, score1);

    ScoreModel scoreModel5 = new ScoreModel(userModel1, level2, score1);
    ScoreModel scoreModel6 = new ScoreModel(userModel2, level2, score1);

    ScoreModel scoreModel7 = new ScoreModel(userModel2, level2, score1);
    ScoreModel scoreModel8 = new ScoreModel(userModel2, level1, score1);
    //then
    Assertions.assertEquals(scoreModel1.hashCode(), scoreModel1.hashCode());
    Assertions.assertEquals(scoreModel1.hashCode(), scoreModel2.hashCode());
    Assertions.assertNotEquals(scoreModel3.hashCode(), scoreModel4.hashCode());
    Assertions.assertNotEquals(scoreModel5.hashCode(), scoreModel6.hashCode());
    Assertions.assertNotEquals(scoreModel7.hashCode(), scoreModel8.hashCode());
  }

  @Test
  void userModel() {
    //given
    UserModel userModel1 = Mockito.mock(UserModel.class);
    UserModel userModel2 = null;
    //when
    Executable executable = () -> new ScoreModel(userModel1, 1, 2);
    Executable badexecutable = () -> new ScoreModel(userModel2, 1, 2);
    //then
    Assertions.assertDoesNotThrow(executable);
    Assertions.assertThrows(NullPointerException.class, badexecutable);
    Assertions.assertEquals(userModel1, (new ScoreModel(userModel1, 1, 2)).userModel());
  }

  @Test
  void level() {
    //given
    UserModel userModel = Mockito.mock(UserModel.class);
    int level = 1;
    int badLevel = -1;
    //when
    Executable executable = () -> new ScoreModel(userModel, level, 2);
    Executable badexecutable = () -> new ScoreModel(userModel, badLevel, 2);
    //then
    Assertions.assertDoesNotThrow(executable);
    Assertions.assertThrows(InputMismatchException.class, badexecutable);
    Assertions.assertEquals(level, (new ScoreModel(userModel, level, 2)).level());
  }

  @Test
  void score() {
    //given
    UserModel userModel = Mockito.mock(UserModel.class);
    int score = 1;
    int badScore = -1;
    //when
    Executable executable = () -> new ScoreModel(userModel, 2, score);
    Executable badexecutable = () -> new ScoreModel(userModel, 2, badScore);
    //then
    Assertions.assertDoesNotThrow(executable);
    Assertions.assertThrows(InputMismatchException.class, badexecutable);
    Assertions.assertEquals(score, (new ScoreModel(userModel, 2, score)).score());
  }
}