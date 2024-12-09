package com.example.config.autoconfig;

import com.example.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@MyAutoConfiguration
public class DispatcherServletConfig {

  @Bean
  public DispatcherServlet dispatcherServlet() {
    /**
     * 명시적으로 applicationContext 를 주입하지않아도 상위 인터페이스에서 setter 가 선언되어있기때문에 스프링이 자동으로 주입해줌.
     * @see {ApplicationContextAware}
     */
    return new DispatcherServlet();
  }
}
