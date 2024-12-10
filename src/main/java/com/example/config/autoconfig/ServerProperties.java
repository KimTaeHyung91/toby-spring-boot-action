package com.example.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;

public class ServerProperties {

  @Value("${contextPath}")
  private String contextPath;

  // : 이후에는 default 값이 지정
  @Value("${port:8080}")
  private int port;

  public String getContextPath() {
    return contextPath;
  }

  public int getPort() {
    return port;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
