package com.king.configuration;

import com.king.exception.InternalServerErrorException;
import com.king.exception.ServerInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.util.Properties;

@ExtendWith(MockitoExtension.class)
class FileBackedConfigTest {

  @InjectMocks
  private FileBackedConfig fileBackedConfig;

  @Spy
  private Properties properties;

  @Test
  void getPort() {
    //given
    String port = "123";
    ReflectionTestUtils.setField(fileBackedConfig, "properties", properties);
    //when
    BDDMockito.when(properties.getProperty("highscore.port")).thenReturn(port);
    //then
    Assertions.assertEquals(Integer.valueOf(port), fileBackedConfig.getPort());
  }

  @Test
  void getSessionTimeToLive() throws InternalServerErrorException {
    //given
    String time = "PT10M";
    ReflectionTestUtils.setField(fileBackedConfig, "properties", properties);
    //when
    BDDMockito.when(properties.getProperty("highscore.sessionTimeToLive")).thenReturn(time);
    //then
    Assertions.assertEquals(Duration.parse(time), fileBackedConfig.getSessionTimeToLive());
  }

  @Test
  void getPortThrow() {
    //given
    ReflectionTestUtils.setField(fileBackedConfig, "properties", properties);
    //when
    BDDMockito.when(properties.getProperty("highscore.port")).thenReturn("1.2");
    Executable executable = () -> fileBackedConfig.getPort();
    //then
    Assertions.assertThrows(ServerInitializationException.class, executable);
  }

  @Test
  void getSessionTimeToLiveThrow() throws InternalServerErrorException {
    //given
    ReflectionTestUtils.setField(fileBackedConfig, "properties", properties);
    //when
    BDDMockito.when(properties.getProperty("highscore.sessionTimeToLive")).thenReturn("10min");
    Executable executable = () -> fileBackedConfig.getSessionTimeToLive();
    //then
    Assertions.assertThrows(ServerInitializationException.class, executable);
  }
}