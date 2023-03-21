package com.king.view;


import com.king.exception.BadRequestException;
import com.king.exception.InternalServerErrorException;
import com.king.exception.MethodNotAllowedException;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;

@ExtendWith(MockitoExtension.class)
class HighScoreListViewTest {

  @InjectMocks
  private HighScoreListView highScoreListView;

  @Mock
  private HttpExchange httpExchange;

  @Mock
  private OutputStream outputStream;

  @Test
  void get() {
    //given
    Object[] data = new Object[]{1};
    BDDMockito.given(httpExchange.getResponseBody()).willReturn(outputStream);
    //when
    Executable executable = () -> highScoreListView.get(httpExchange, data);
    //then
    Assertions.assertDoesNotThrow(executable);
  }

  @Test
  void getIOException() throws IOException {
    //given
    Object[] data = new Object[]{1};
    BDDMockito.given(httpExchange.getResponseBody()).willReturn(outputStream);
    //when
    BDDMockito.doThrow(IOException.class).when(outputStream).write(Mockito.any());
    Executable executable = () -> highScoreListView.get(httpExchange, data);
    //then
    Assertions.assertThrows(InternalServerErrorException.class, executable);
  }

  @Test
  void getMismatchExchange() {
    //given
    Object[] data = new Object[]{1};
    //when
    Executable executable = () -> highScoreListView.get(null, data);
    //then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void getMismatchData() {
    //given
    Object[] data1 = null;
    Object[] data2 = new Object[]{1, 2};
    Object[] data3 = new Object[]{null};
    Object[] data4 = new Object[]{-1};
    //when
    Executable executable1 = () -> highScoreListView.get(httpExchange, data1);
    Executable executable2 = () -> highScoreListView.get(httpExchange, data2);
    Executable executable3 = () -> highScoreListView.get(httpExchange, data3);
    Executable executable4 = () -> highScoreListView.get(httpExchange, data4);
    //then
    Assertions.assertThrows(InputMismatchException.class, executable1);
    Assertions.assertThrows(InputMismatchException.class, executable2);
    Assertions.assertThrows(InputMismatchException.class, executable3);
    Assertions.assertThrows(BadRequestException.class, executable4);
  }

  @Test
  void methodsNotDeveloper(){
    //given
    //when
    Executable executable = () -> highScoreListView.post(httpExchange, new Object[]{});
    Executable executable1 = () -> highScoreListView.put(httpExchange, new Object[]{});
    Executable executable2 = () -> highScoreListView.delete(httpExchange, new Object[]{});
    //then
    Assertions.assertThrows(MethodNotAllowedException.class, executable);
    Assertions.assertThrows(MethodNotAllowedException.class, executable1);
    Assertions.assertThrows(MethodNotAllowedException.class, executable2);
  }
}