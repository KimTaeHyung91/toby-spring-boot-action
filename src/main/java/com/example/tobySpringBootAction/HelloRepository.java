package com.example.tobySpringBootAction;

import java.util.Comparator;

public interface HelloRepository {

  Hello findHello(String name);

  void increaseCount(String name);

  /**
   * Java 8 에 추가된 문법
   * <p>
   * 인터페이스 메소드를 길게 유지 안해도 되고 기존 선언된 메소드를 재사용하는 측면에서 용이함.
   * <p>
   * 해당 클래스 참조, default 메소드 많이 활용
   *
   * @see Comparator
   */
  default int count(String name) {
    Hello hello = findHello(name);

    return hello == null ? 0 : hello.getCount();
  }
}
