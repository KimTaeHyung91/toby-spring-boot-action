package com.example.tobySpringBootAction;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
// ANNOTATION_TYPE 은 Meta 에노테이션이 됨. 다른 에노테이션을 만들때 Meta 에노테이션의 정보를 모두 사용 가능(상속X)
@Test
@interface UnitTest {}

public class HelloServiceTest {

  // 서버를 실행 시켜서 테스트를 진행 할 수 있지만 아래처럼 일반 자바 객체를 생성해서 테스트 시 빠른 검증이 가능
  // 또한 테스트를 고립된 상태에서 진행 가능
  @Test
  void simpleHelloService() {
    SimpleHelloService simpleHelloService = new SimpleHelloService(helloRepositoryStub);

    String test = simpleHelloService.sayHello("Test");

    Assertions.assertThat(test).isEqualTo("Hello, Test");
  }

  private static final HelloRepository helloRepositoryStub = new HelloRepository() {
    @Override
    public Hello findHello(String name) {
      return null;
    }

    @Override
    public void increaseCount(String name) {
    }
  };


  @Test
  void helloDecorator() {
    HelloDecorator helloDecorator = new HelloDecorator(name -> name);

    String test = helloDecorator.sayHello("Test");

    Assertions.assertThat(test).isEqualTo("*Test*");
  }
}
