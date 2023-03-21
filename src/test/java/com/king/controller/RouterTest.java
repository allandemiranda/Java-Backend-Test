package com.king.controller;

import com.king.exception.MethodNotAllowedException;
import com.king.exception.NotFoundException;
import com.king.exception.NotImplementedException;
import com.king.model.HttpRequestModel;
import com.king.model.UserModel;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.OutputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class RouterTest {

  @Mock
  private HttpRequestModel httpRequestModel;

  @Mock
  private AbstractMap.SimpleEntry<Integer, HttpPath> pathParser;

  @Mock
  private HttpExchange httpExchange;

  @Mock
  private OutputStream outputStream;

  @Test
  void loginGet() {
    //given
    int userId = 1;
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.LOGIN);
    BDDMockito.given(pathParser.getKey()).willReturn(userId);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.GET);
    BDDMockito.given(httpExchange.getResponseBody()).willReturn(outputStream);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void loginPost() {
    //given
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.LOGIN);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.POST);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertThrows(MethodNotAllowedException.class, executable);
  }

  @Test
  void scoreGet() {
    //given
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.SCORE);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.GET);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertThrows(MethodNotAllowedException.class, executable);
  }

  @Test
  void scorePost() {
    //given
    int userId = 1;
    String sessionkey = "sessionkey";
    Map<String, String> mapKeys = new HashMap<>();
    UserModel userModel = new UserModel(userId);
    mapKeys.put(sessionkey, SessionKey.getKey(userModel));
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.SCORE);
    BDDMockito.given(pathParser.getKey()).willReturn(userId);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.POST);
    BDDMockito.given(httpRequestModel.parameters()).willReturn(mapKeys);
    //when
    BDDMockito.doReturn(String.valueOf(userId)).when(httpRequestModel).body();
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void highScoreListGet() {
    //given
    int userId = 1;
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.HIGHSCORELIST);
    BDDMockito.given(pathParser.getKey()).willReturn(userId);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.GET);
    BDDMockito.given(httpExchange.getResponseBody()).willReturn(outputStream);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void highScoreListPost() {
    //given
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.HIGHSCORELIST);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.POST);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertThrows(MethodNotAllowedException.class, executable);
  }

  @Test
  void notFound() {
    //given
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.NOT_FOUND);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertThrows(NotFoundException.class, executable);
  }

  @Test
  void unsupported() {
    //given
    BDDMockito.given(pathParser.getValue()).willReturn(HttpPath.LOGIN);
    BDDMockito.given(httpRequestModel.httpMethod()).willReturn(HttpMethod.UNSUPPORTED);
    //when
    Executable executable = () -> Router.inject(httpExchange, httpRequestModel, pathParser);
    //then
    Assertions.assertThrows(NotImplementedException.class, executable);
  }
}