package com.king.controller;

import com.king.exception.BadRequestException;
import com.king.exception.NotFoundException;
import com.king.model.HttpRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HttpPathParserTest {

  @Mock
  HttpRequestModel httpRequestModel;

  @Test
  void decodePath() {
    //given
    try (MockedStatic<HttpPath> httpPath = Mockito.mockStatic(HttpPath.class)) {
      String user_1 = "/1";
      BDDMockito.given(httpRequestModel.path()).willReturn(user_1);
      //when
      httpPath.when(() -> HttpPath.path(Mockito.anyString())).thenReturn(HttpPath.LOGIN);
      Executable executable = () -> HttpPathParser.decodePath(httpRequestModel);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void badInput() {
    //given
    try (MockedStatic<HttpPath> httpPath = Mockito.mockStatic(HttpPath.class)) {
      //when
      httpPath.when(() -> HttpPath.path(Mockito.anyString())).thenReturn(HttpPath.LOGIN);
      Executable executable = () -> HttpPathParser.decodePath(httpRequestModel);
      //then
      Assertions.assertThrows(BadRequestException.class, executable);
    }
  }

  @Test
  void pathNotFound() {
    //given
    String wrongPath = "/abc";
    //when
    Mockito.when(httpRequestModel.path()).thenReturn(wrongPath);
    Executable executable = () -> HttpPathParser.decodePath(httpRequestModel);
    //then
    Assertions.assertThrows(NotFoundException.class, executable);

  }
}