package com.example.tobySpringBootAction;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

  // 서버를 실행 시켜서 테스트를 진행 할 수 있지만 아래처럼 일반 자바 객체를 생성해서 테스트 시 빠른 검증이 가능
  // 또한 테스트를 고립된 상태에서 진행 가능
  @Test
  void simpleHelloService() {
    SimpleHelloService simpleHelloService = new SimpleHelloService();

    String test = simpleHelloService.sayHello("Test");

    Assertions.assertThat(test).isEqualTo("Hello, Test");
  }

  @Test
  void helloDecorator() {
    HelloDecorator helloDecorator = new HelloDecorator(name -> name);

    String test = helloDecorator.sayHello("Test");

    Assertions.assertThat(test).isEqualTo("*Test*");
  }
}
