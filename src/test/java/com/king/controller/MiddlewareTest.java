package com.king.controller;

import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.exception.MethodNotAllowedException;
import com.king.exception.NotFoundException;
import com.king.exception.NotImplementedException;
import com.king.exception.UnauthorizedException;
import com.king.model.HttpRequestModel;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.AbstractMap;

@ExtendWith(MockitoExtension.class)
class MiddlewareTest {

  @InjectMocks
  private Middleware middleware;

  @Mock
  private HttpExchange exchange;

  @Mock
  private HttpRequestModel httpRequestModel;

  @Test
  void handle() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class);
         MockedStatic<Router> routerMockedStatic = Mockito.mockStatic(Router.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenReturn(new AbstractMap.SimpleEntry<Integer, HttpPath>(null, null));
      routerMockedStatic.when(() -> Router.inject(Mockito.any(), Mockito.any(), Mockito.any())).thenAnswer((Answer<Void>) invocation -> null);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void notImplementedException() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class);
         MockedStatic<Router> routerMockedStatic = Mockito.mockStatic(Router.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenReturn(new AbstractMap.SimpleEntry<Integer, HttpPath>(null, null));
      routerMockedStatic.when(() -> Router.inject(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(NotImplementedException.class);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void notFoundException() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenThrow(NotFoundException.class);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void badRequestException() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class);
         MockedStatic<Router> routerMockedStatic = Mockito.mockStatic(Router.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenReturn(new AbstractMap.SimpleEntry<Integer, HttpPath>(null, null));
      routerMockedStatic.when(() -> Router.inject(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(BadRequestException.class);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void methodNotAllowedException() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class);
         MockedStatic<Router> routerMockedStatic = Mockito.mockStatic(Router.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenReturn(new AbstractMap.SimpleEntry<Integer, HttpPath>(null, null));
      routerMockedStatic.when(() -> Router.inject(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(MethodNotAllowedException.class);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void unauthorizedException() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class);
         MockedStatic<Router> routerMockedStatic = Mockito.mockStatic(Router.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenReturn(new AbstractMap.SimpleEntry<Integer, HttpPath>(null, null));
      routerMockedStatic.when(() -> Router.inject(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(UnauthorizedException.class);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void internalServerErrorException() {
    //given
    try (MockedStatic<HttpRequestParser> httpRequestParserMockedStatic = Mockito.mockStatic(HttpRequestParser.class);
         MockedStatic<HttpPathParser> httpPathParserMockedStatic = Mockito.mockStatic(HttpPathParser.class);
         MockedStatic<Router> routerMockedStatic = Mockito.mockStatic(Router.class)) {
      //when
      httpRequestParserMockedStatic.when(() -> HttpRequestParser.parse(Mockito.any())).thenReturn(httpRequestModel);
      httpPathParserMockedStatic.when(() -> HttpPathParser.decodePath(httpRequestModel)).thenReturn(new AbstractMap.SimpleEntry<Integer, HttpPath>(null, null));
      routerMockedStatic.when(() -> Router.inject(Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(InternalServerErrorException.class);
      Executable executable = () -> middleware.handle(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void anyException() {
    //given
    //when
    Executable executable = () -> middleware.handle(exchange);
    //then
    Assertions.assertDoesNotThrow(executable);
  }
}