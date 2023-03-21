package com.king.controller;

import com.king.exception.InternalServerErrorException;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class HttpRequestParserTest {

  @Mock
  private InputStream inputStream;

  @Mock
  private URI uri;

  @Test
  void parse() throws IOException {
    //given
    try (HttpExchange exchange = Mockito.mock(HttpExchange.class)) {
      BDDMockito.given(exchange.getRequestBody()).willReturn(inputStream);
      BDDMockito.given(inputStream.readAllBytes()).willReturn(new byte[]{});
      BDDMockito.given(exchange.getRequestURI()).willReturn(uri);
      BDDMockito.given(uri.getPath()).willReturn("");
      BDDMockito.given(exchange.getRequestMethod()).willReturn("get");
      //when
      Executable executable = () -> HttpRequestParser.parse(exchange);
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }

  @Test
  void getParameters() throws IOException, InternalServerErrorException {
    //given
    try (HttpExchange exchange = Mockito.mock(HttpExchange.class)) {
      Map<String, String> expected = new HashMap<>();
      expected.put("a", "1");
      expected.put("b", "2");
      BDDMockito.given(exchange.getRequestBody()).willReturn(inputStream);
      BDDMockito.given(inputStream.readAllBytes()).willReturn(new byte[]{});
      BDDMockito.given(exchange.getRequestURI()).willReturn(uri);
      BDDMockito.given(uri.getPath()).willReturn("");
      BDDMockito.given(exchange.getRequestMethod()).willReturn("get");
      BDDMockito.given(uri.getQuery()).willReturn("a=1&b=2");
      //when
      //then
      Assertions.assertEquals(HttpRequestParser.parse(exchange).parameters(), expected);
    }
  }

  @Test
  void parseThrow() throws IOException {
    //given
    try (HttpExchange exchange = Mockito.mock(HttpExchange.class)) {
      BDDMockito.given(exchange.getRequestBody()).willReturn(inputStream);
      BDDMockito.given(inputStream.readAllBytes()).willThrow(IOException.class);
      //when
      Executable executable = () -> HttpRequestParser.parse(exchange);
      //then
      Assertions.assertThrows(InternalServerErrorException.class, executable);
    }
  }
}