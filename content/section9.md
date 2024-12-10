## 외부 설정을 이용한 자동 구성

<div>

```mermaid

flowchart TD
    imports["MyAutoConfiguration.imports"]
    auto["자동 구성 정보 로딩"]
    subgraph AutoLoading
        imports -.-> auto
    end

    starter["Starter, Dependency Classpath Class"]
    check1["@Conditional, Class 조건 체크"]
    subgraph ConditionCheck
        starter -.-> check1
    end
    auto --> check1
    check1 --> check2
    check2["@Conditional, @Bean 조건 체크"]
    custom["커스텀 @Bean 구성정보"]
    env["Environment Properties"]
    myAuto["@MyAutoConfiguration + @Bean 구성정보"]
    subgraph environment
        env -.-> myAuto
    end
    check2 -- MissingBean = false --> custom
    check2 -- MissingBean = true --> myAuto
```

보통 자동 구성 정보에는 default 값이 다 정해짐

e.g) tomcat - port: 8080

만약 default 값을 필요한 경우를 바꾸고 싶다면?

Configuration 클래스에 다양한 프로퍼티를 변경할 수 있게 Spring 에서는 Environment 추상화를 통해서 가능

```mermaid
flowchart
    subgraph Environment Abstraction - Properties
        sp["System Properties - StandardEnvironment"]
        sev["System Environment Variables - StandardServletEnvironment"]
        scp["ServletConfig Parameters - StandardServletEnvironment"]
        scp2["ServletContext Parameters - StandardServletEnvironment"]
        jndi["JNDI - StandardServletEnvironment"]
        ps["@PropertySource - StandardServletEnvironment"]
        ap["application.properties, xml, yml - SpringBoot"]
    end

    markdown["`Environment.getProperty({property.name})`"]

```

우선 순위에 따라 환경 변수 값이 적용됨, 아래는 적용 순서를 표현

```mermaid
flowchart TD
    sp["System Properties - StandardEnvironment"]
    sev["System Environment Variables - StandardServletEnvironment"]
    scp["ServletConfig Parameters - StandardServletEnvironment"]
    scp2["ServletContext Parameters - StandardServletEnvironment"]
    jndi["JNDI - StandardServletEnvironment"]
    ps["@PropertySource - StandardServletEnvironment"]
    ap["application.properties, xml, yml - SpringBoot"]
    scp --> scp2
    scp2 --> jndi
    jndi --> sp
    sp --> sev
    sev --> ps
    ps --> ap
```

</div>
