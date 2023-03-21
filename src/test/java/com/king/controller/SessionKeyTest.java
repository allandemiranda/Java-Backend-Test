package com.king.controller;

import com.king.exception.BadRequestException;
import com.king.exception.UnauthorizedException;
import com.king.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class SessionKeyTest {

  private static String keyFake;

  @BeforeAll
  static void keyFake() {
    UserModel userModel = new UserModel(144);
    LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(12);
    try (MockedStatic<LocalDateTime> localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
      localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);
      keyFake = SessionKey.getKey(userModel);
    }
  }

  @Test
  void flow() throws UnauthorizedException, BadRequestException {
    //given
    UserModel userModel = new UserModel(144);
    //when
    String key = SessionKey.getKey(userModel);
    //then
    Assertions.assertEquals(userModel, SessionKey.getUser(key));
  }

  @Test
  void unauthorizedException() {
    //given
    //when
    Executable executable = () -> SessionKey.getUser(keyFake);
    //then
    Assertions.assertThrows(UnauthorizedException.class, executable);
  }

}