package com.king.view;

import com.king.controller.SessionKey;
import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.exception.MethodNotAllowedException;
import com.king.model.HttpRequestModel;
import com.king.model.UserModel;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ScoreViewTest {

  public static final String SESSIONKEY = "sessionkey";

  @InjectMocks
  private ScoreView scoreView;

  @Mock
  private HttpRequestModel httpRequestModel;

  @Mock
  private HttpExchange httpExchange;

  @Test
  void post() {
    //given
    Object[] data = new Object[]{1, httpRequestModel};
    Map<String, String> mapKeys = new HashMap<>();
    UserModel userModel = new UserModel(1);
    mapKeys.put(SESSIONKEY, SessionKey.getKey(userModel));
    BDDMockito.given(httpRequestModel.parameters()).willReturn(mapKeys);
    //when
    BDDMockito.doReturn(String.valueOf(userModel.id())).when(httpRequestModel).body();
    Executable executable = () -> scoreView.post(httpExchange, data);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void postNumberFormation() {
    //given
    Object[] data = new Object[]{1, httpRequestModel};
    Map<String, String> mapKeys = new HashMap<>();
    UserModel userModel = new UserModel(1);
    mapKeys.put(SESSIONKEY, SessionKey.getKey(userModel));
    BDDMockito.given(httpRequestModel.parameters()).willReturn(mapKeys);
    //when
    BDDMockito.doReturn("1.5").when(httpRequestModel).body();
    Executable executable = () -> scoreView.post(httpExchange, data);
    //then
    Assertions.assertThrows(BadRequestException.class, executable);
  }

  @Test
  void postExchangeThrow() throws IOException {
    //given
    Object[] data = new Object[]{1, httpRequestModel};
    Map<String, String> mapKeys = new HashMap<>();
    UserModel userModel = new UserModel(1);
    mapKeys.put(SESSIONKEY, SessionKey.getKey(userModel));
    BDDMockito.given(httpRequestModel.parameters()).willReturn(mapKeys);
    //when
    BDDMockito.doReturn(String.valueOf(userModel.id())).when(httpRequestModel).body();
    BDDMockito.doThrow(IOException.class).when(httpExchange).sendResponseHeaders(201, 0);
    Executable executable = () -> scoreView.post(httpExchange, data);
    //then
    Assertions.assertThrows(InternalServerErrorException.class, executable);
  }

  @Test
  void postOutSessionKey() {
    //given
    Object[] data = new Object[]{1, httpRequestModel};
    Map<String, String> mapKeys = new HashMap<>();
    UserModel userModel = new UserModel(1);
    mapKeys.put("", SessionKey.getKey(userModel));
    BDDMockito.given(httpRequestModel.parameters()).willReturn(mapKeys);
    //when
    Executable executable = () -> scoreView.post(httpExchange, data);
    //then
    Assertions.assertThrows(BadRequestException.class, executable);
  }

  @Test
  void postMismatchExchange() {
    //given
    Object[] data = new Object[]{1, 2};
    //when
    Executable executable = () -> scoreView.post(null, data);
    //then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void postMismatchData() {
    //given
    Object[] data1 = null;
    Object[] data2 = new Object[]{1};
    Object[] data3 = new Object[]{null, httpRequestModel};
    Object[] data4 = new Object[]{1, null};
    //when
    Executable executable1 = () -> scoreView.post(httpExchange, data1);
    Executable executable2 = () -> scoreView.post(httpExchange, data2);
    Executable executable3 = () -> scoreView.post(httpExchange, data3);
    Executable executable4 = () -> scoreView.post(httpExchange, data4);
    //then
    Assertions.assertThrows(InputMismatchException.class, executable1);
    Assertions.assertThrows(InputMismatchException.class, executable2);
    Assertions.assertThrows(InputMismatchException.class, executable3);
    Assertions.assertThrows(InputMismatchException.class, executable4);
  }

  @Test
  void methodsNotDeveloper(){
    //given
    //when
    Executable executable = () -> scoreView.get(httpExchange, new Object[]{});
    Executable executable_1 = () -> scoreView.put(httpExchange, new Object[]{});
    Executable executable_2 = () -> scoreView.delete(httpExchange, new Object[]{});
    //then
    Assertions.assertThrows(MethodNotAllowedException.class, executable);
    Assertions.assertThrows(MethodNotAllowedException.class, executable_1);
    Assertions.assertThrows(MethodNotAllowedException.class, executable_2);
  }
}