package com.example.tobySpringBootAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 합성 에노테이션 제작
 */
@Retention(RetentionPolicy.RUNTIME) // 기본값은 CLASS 즉 컴파일 당시에는 해당 에노테이션이 적용되지만 런타임 시 에는 적용X
@Target(ElementType.TYPE) // CLASS, INTERFACE, ENUM
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {
}
