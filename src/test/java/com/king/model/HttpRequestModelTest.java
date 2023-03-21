package com.king.model;

import com.king.controller.HttpMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class HttpRequestModelTest {

  private final HttpMethod httpMethod = Mockito.mock(HttpMethod.class);
  private final String path = "";
  private final String body = "";
  private final Map<String, String> parameters = new HashMap<>();

  @Test
  void httpMethod() {
    //given
    HttpMethod httpMethod = null;
    //when
    Executable executable = () -> new HttpRequestModel(httpMethod, path, body, parameters);
    //then
    Assertions.assertThrows(NullPointerException.class, executable);
  }

  @Test
  void path() {
    //given
    String path = null;
    //when
    Executable executable = () -> new HttpRequestModel(httpMethod, path, body, parameters);
    //then
    Assertions.assertThrows(NullPointerException.class, executable);
  }

  @Test
  void body() {
    //given
    String body = null;
    //when
    Executable executable = () -> new HttpRequestModel(httpMethod, path, body, parameters);
    //then
    Assertions.assertThrows(NullPointerException.class, executable);
  }

  @Test
  void parameters() {
    //given
    Map<String, String> parameters = null;
    //when
    Executable executable = () -> new HttpRequestModel(httpMethod, path, body, parameters);
    //then
    Assertions.assertThrows(NullPointerException.class, executable);
  }

  @Test
  void goodCheck() {
    //given
    //when
    Executable executable = () -> new HttpRequestModel(httpMethod, path, body, parameters);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void testMethods(){
    //given
    //when
    HttpRequestModel model = new HttpRequestModel(httpMethod, path, body, parameters);
    //then
    Assertions.assertEquals(httpMethod, model.httpMethod());
    Assertions.assertEquals(path, model.path());
    Assertions.assertEquals(body, model.body());
    Assertions.assertEquals(parameters, model.parameters());
  }
}