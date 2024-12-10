package com.example.tobySpringBootAction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HelloBootTest
public class HelloRepositoryTest {

  @Autowired
  HelloRepository helloRepository;

  @Test
  void findHelloFailed() {
    assertThat(helloRepository.findHello("James")).isNull();

  }

  @Test
  void increaseCount() {
    assertThat(helloRepository.count("James")).isEqualTo(0);

    helloRepository.increaseCount("James");
    assertThat(helloRepository.count("James")).isEqualTo(1);

    helloRepository.increaseCount("James");
    assertThat(helloRepository.count("James")).isEqualTo(2);

  }
}
