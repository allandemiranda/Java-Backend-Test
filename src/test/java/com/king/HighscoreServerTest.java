package com.king;

import com.king.configuration.FileBackedConfig;
import com.king.exception.ServerInitializationException;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.net.ServerSocket;

@ExtendWith(MockitoExtension.class)
class HighscoreServerTest {

  @Mock
  HttpServer httpServer;

  @Test
  void serverInitializationException() {
    //given
    try (MockedStatic<HttpServer> httpServerMockedStatic = Mockito.mockStatic(HttpServer.class)) {
      //when
      httpServerMockedStatic.when(() -> HttpServer.create(Mockito.any(), Mockito.anyInt())).thenThrow(IOException.class);
      Executable executable = () -> HighscoreServer.main(new String[]{});
      //then
      Assertions.assertThrows(ServerInitializationException.class, executable);
    }
  }

  @Test
  void running() throws IOException {
    //given
    try (ServerSocket socket = new ServerSocket(0);
         MockedStatic<HttpServer> httpServerMockedStatic = Mockito.mockStatic(HttpServer.class);
         MockedConstruction<FileBackedConfig> mockedConstruction = Mockito.mockConstruction(
           FileBackedConfig.class,
           (mock, context) -> BDDMockito.given(mock.getPort()).willReturn(socket.getLocalPort()))
    ) {
      //when
      mockedConstruction.constructed();
      httpServerMockedStatic.when(() -> HttpServer.create(Mockito.any(), Mockito.anyInt())).thenReturn(httpServer);
      BDDMockito.when(httpServer.createContext(Mockito.anyString(), Mockito.any())).thenAnswer((Answer<Void>) invocation -> null);
      BDDMockito.doNothing().when(httpServer).start();
      Executable executable = () -> HighscoreServer.main(new String[]{});
      //then
      Assertions.assertDoesNotThrow(executable);
    }
  }
}