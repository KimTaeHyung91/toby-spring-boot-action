package com.example.tobySpringBootAction;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

// @Component 를 포함하고 있음.
@Configuration
public class Config {

  @Bean
  public ServletWebServerFactory servletWebServerFactory() {
    return new TomcatServletWebServerFactory();
  }

  @Bean
  public DispatcherServlet dispatcherServlet() {
    /**
     * 명시적으로 applicationContext 를 주입하지않아도 상위 인터페이스에서 setter 가 선언되어있기때문에 스프링이 자동으로 주입해줌.
     * @see {ApplicationContextAware}
     */
    return new DispatcherServlet();
  }

}
