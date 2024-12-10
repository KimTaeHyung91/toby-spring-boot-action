package com.example.tobySpringBootAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


/**
 * 테스트 코드 내에서 Spring Container 를 띄우고, 등록된 빈 구성 정보를 가져와서 테스트
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloBootApplication.class)
@TestPropertySource("classpath:/application.properties")
// 테스트 코드에서 사용 사 모든 테스트 케이스를 수행 후 모든 작업에 대해서 rollback까지 진행
@Transactional
public @interface HelloBootTest {
}
