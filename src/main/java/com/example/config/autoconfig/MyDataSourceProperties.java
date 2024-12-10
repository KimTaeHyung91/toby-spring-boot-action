package com.example.config.autoconfig;

import com.example.config.MyConfigurationProperties;

@MyConfigurationProperties(prefix = "data")
public class MyDataSourceProperties {

  private String driverClassName;

  private String url;

  private String userName;

  private String password;


  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "MyDataSourceProperties{" +
        "driverClassName='" + driverClassName + '\'' +
        ", url='" + url + '\'' +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
