package com.king.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HttpPathTest {

  @Test
  void login() {
    //given
    String request1 = "/123/login";
    String request2 = "/123/login/";
    String request3 = "/123/Login";
    String request4 = "123/login";
    String request5 = "123/login/";
    String request6 = "/123/login/abc";
    String request7 = "/abc/login";
    String request8 = "/1.2/login";
    //when
    HttpPath httpPath1 = HttpPath.path(request1);
    HttpPath httpPath2 = HttpPath.path(request2);
    HttpPath httpPath3 = HttpPath.path(request3);
    HttpPath httpPath4 = HttpPath.path(request4);
    HttpPath httpPath5 = HttpPath.path(request5);
    HttpPath httpPath6 = HttpPath.path(request6);
    HttpPath httpPath7 = HttpPath.path(request7);
    HttpPath httpPath8 = HttpPath.path(request8);
    //then
    Assertions.assertEquals(HttpPath.LOGIN, httpPath1);
    Assertions.assertEquals(HttpPath.LOGIN, httpPath2);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath3);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath4);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath5);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath6);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath7);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath8);
  }

  @Test
  void score() {
    //given
    String request1 = "/123/score";
    String request2 = "/123/score/";
    String request3 = "/123/Score";
    String request4 = "123/score";
    String request5 = "123/score/";
    String request6 = "/123/score/abc";
    String request7 = "/abc/score";
    String request8 = "/1.2/score";
    //when
    HttpPath httpPath1 = HttpPath.path(request1);
    HttpPath httpPath2 = HttpPath.path(request2);
    HttpPath httpPath3 = HttpPath.path(request3);
    HttpPath httpPath4 = HttpPath.path(request4);
    HttpPath httpPath5 = HttpPath.path(request5);
    HttpPath httpPath6 = HttpPath.path(request6);
    HttpPath httpPath7 = HttpPath.path(request7);
    HttpPath httpPath8 = HttpPath.path(request8);
    //then
    Assertions.assertEquals(HttpPath.SCORE, httpPath1);
    Assertions.assertEquals(HttpPath.SCORE, httpPath2);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath3);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath4);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath5);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath6);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath7);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath8);
  }

  @Test
  void highscorelist() {
    //given
    String request1 = "/123/highscorelist";
    String request2 = "/123/highscorelist/";
    String request3 = "/123/Highscorelist";
    String request4 = "123/highscorelist";
    String request5 = "123/highscorelist/";
    String request6 = "/123/highscorelist/abc";
    String request7 = "/abc/highscorelist";
    String request8 = "/1.2/highscorelist";
    //when
    HttpPath httpPath1 = HttpPath.path(request1);
    HttpPath httpPath2 = HttpPath.path(request2);
    HttpPath httpPath3 = HttpPath.path(request3);
    HttpPath httpPath4 = HttpPath.path(request4);
    HttpPath httpPath5 = HttpPath.path(request5);
    HttpPath httpPath6 = HttpPath.path(request6);
    HttpPath httpPath7 = HttpPath.path(request7);
    HttpPath httpPath8 = HttpPath.path(request8);
    //then
    Assertions.assertEquals(HttpPath.HIGHSCORELIST, httpPath1);
    Assertions.assertEquals(HttpPath.HIGHSCORELIST, httpPath2);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath3);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath4);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath5);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath6);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath7);
    Assertions.assertEquals(HttpPath.NOT_FOUND, httpPath8);
  }
}