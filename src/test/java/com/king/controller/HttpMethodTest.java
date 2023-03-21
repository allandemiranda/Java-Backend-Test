package com.king.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HttpMethodTest {

  @Test
  void get() {
    //given
    String request = "get";
    //when
    HttpMethod httpMethod = HttpMethod.from(request);
    //then
    Assertions.assertEquals(HttpMethod.GET, httpMethod);
  }

  @Test
  void post() {
    //given
    String request = "post";
    //when
    HttpMethod httpMethod = HttpMethod.from(request);
    //then
    Assertions.assertEquals(HttpMethod.POST, httpMethod);
  }

  @Test
  void put() {
    //given
    String request = "put";
    //when
    HttpMethod httpMethod = HttpMethod.from(request);
    //then
    Assertions.assertEquals(HttpMethod.UNSUPPORTED, httpMethod);
  }

  @Test
  void delete() {
    //given
    String request = "delete";
    //when
    HttpMethod httpMethod = HttpMethod.from(request);
    //then
    Assertions.assertEquals(HttpMethod.UNSUPPORTED, httpMethod);
  }
}