## 스프링 부트의 자동 구성 그 중 핵심이라고 불리우는 조건부 자동 구성

<div>

### 조건부 자동 구성이란?

먼저 스프링 부트에서 자동 구성으로 imports 하는 구성 정보를 확인해보자

spring-boot-autoconfigure-2.7.6.jar!
/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

-> 해당 파일안에는 총 144개의 구성정보가 포함됨.

우리가 Hello, World 라는 문자열을 출력할때도 적어도 144개 이상 빈들이 로딩이됨.

내가 만약 144개의 구성정보를 포함하는 빈중에 사용안하는게 존재한다면? 조건부로 자동 로딩

### Condition 설계

Bean Factory 메소드에도 Conditional 을 적용 가능

그리고 Condition 을 구현하는 메소드에서 true 이면 빈 등록, false 도 빈 등록X

적용 Scope: Class -> Method

e.g) Class 레벨에서 false, Method 레벨에서 true 이면 애초에 Method 레벨은 검사X
Class 레벨에서 true 가 되어야 검사

```mermaid
classDiagram
    direction BT
    class Condition
    class Conditional
    class MyConfiguration
    class MyBean
    <<interface>> Condition
    <<annotation>> Conditional
    <<ConfigurationClass>> MyConfiguration
    <<BeanMethod>> MyBean
    Conditional ..> Condition: element
    MyConfiguration ..> Conditional: annotate
    MyBean ..> Conditional: annotate
    Condition ..> MyConfiguration: matches 결과
    Condition ..> MyBean: matches 결과

```

### Tomcat.class 와 Server.class(jetty) 클래스 유무에 따라 조건부로 구성 정보 로딩

ClassUtil 을 이용해서 체크

```mermaid
classDiagram
    direction BT
    class MyConfig
    class ConditionalMyOnClass
    class Conditional
    class OnMyClassCondition
    class Condition
    <<interface>> Condition
    MyConfig ..> ConditionalMyOnClass: annotate
    ConditionalMyOnClass ..> Conditional: meta annotation
    Conditional --> OnMyClassCondition: element
    OnMyClassCondition ..|> Condition
    ConditionalMyOnClass --> OnMyClassCondition: attribute(element)
    OnMyClassCondition --> MyConfig: matches()
```

### 자동 구성 대체하기

AutoConfiguration 보다 사용자가 직접 빈을 구성정보를 등록하면 Spring Container 에서는 사용자 빈을 우선적으로 등록하고, 그 후 자동 구성 정보를
등록하게 된다.

이때 빈을 등록 하려는 객체 타입이 같아진다면 이미 사용자 구성정보가 다 등록되고 Auto Configuration 을 등록하는거니 @ConditionOnMissingBean 을
이용하자
</div>
