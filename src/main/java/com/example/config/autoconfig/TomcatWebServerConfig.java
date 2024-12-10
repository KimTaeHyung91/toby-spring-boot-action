package com.example.config.autoconfig;

import com.example.config.ConditionalMyOnClass;
import com.example.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

// Tomcat 이외의 다른 서버를 사용 할 수 있으니 분리
@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

  @Bean("tomcatWebServerFactory")
  @ConditionalOnMissingBean
  public ServletWebServerFactory servletWebServerFactory() {
    return new TomcatServletWebServerFactory();
  }
}
