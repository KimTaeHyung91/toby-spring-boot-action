package com.example.tobySpringBootAction;

import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HelloBootTest

public class HelloServiceCountTest {

  @Autowired
  private HelloRepository helloRepository;

  @Autowired
  HelloService helloService;


  @Test
  void sayHelloIncreaseCount() {
    IntStream.rangeClosed(1, 10).forEach(count -> {
      helloService.sayHello("James");
      Assertions.assertThat(helloRepository.count("James")).isEqualTo(count);
    });
  }
}
