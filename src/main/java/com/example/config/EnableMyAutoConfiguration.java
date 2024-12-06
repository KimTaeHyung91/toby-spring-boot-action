package com.example.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * "@Import" 하는 부분이 계속 늘어나면 가독성이 떨어지니 별도 EnableAutoConfiguration 에노테이션을 생성해서 관리
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigSelector.class) // 다른 패키지에 있는 구성정보를 가져올때 쓰이는 애노테이션
public @interface EnableMyAutoConfiguration {
}
