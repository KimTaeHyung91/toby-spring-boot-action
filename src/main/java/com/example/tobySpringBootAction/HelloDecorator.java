package com.example.tobySpringBootAction;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // HelloService 를 구현한 빈 2개 이상인 경우 최우선적으로 빈으로 등록
public class HelloDecorator implements HelloService {

  private final HelloService helloService;

  public HelloDecorator(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public String sayHello(String name) {
    return "*" + helloService.sayHello(name) + "*";
  }
}
