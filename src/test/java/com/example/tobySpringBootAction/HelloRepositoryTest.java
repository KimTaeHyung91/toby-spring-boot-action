package com.example.tobySpringBootAction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Container 를 띄우는거지만 웹환경은 사용안하겠다는 element
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
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
