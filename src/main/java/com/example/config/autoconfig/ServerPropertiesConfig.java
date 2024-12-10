package com.example.config.autoconfig;

import com.example.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServerPropertiesConfig {

  /**
   * Binder 를 이용해서 environment 가 ServerProperties 값을 get 할때 자동으로 바인딩해줌.
   */
  @Bean
  public ServerProperties serverProperties(Environment environment) {
    return Binder.get(environment).bind("", ServerProperties.class).get();
  }
}
