## 스프링부트 자세히 살펴보기

<div>

스프링부트가 기본적으로 제공하는 다양한 자동 구성을 어떻게 확인해볼 수 있는가?

어떤 라이브러리를 추가하고, 어떤 프로퍼티를 설정하면 혹은 어떤 커스텀 인프라스트럭처 빈을 추가 시 자동 구성이 어떤식으로 대체가 되서 동작하는지 확인 해봐야됨(중요!)

이제 실무에서는 Spring MVC, JPA, Redis, Cloud Service(AWS, GCP, Azure) 해당 자동 구성 정보를 만들때 무슨일 일어나는지 확인 할 수
있어야됨(중요!!)

```mermaid

flowchart
    subgraph "no.1"
        direction TB
        select["사용 기술 선택"]
        si["Spring Initializr"]
        ss["Springboot Starter \n+ Dependency"]
        auto["@AutoConfiguration\nAutoConfiguration.imports"]
        cond["@Conditional"]
        select --> si
        si -- build . gradle생성 --> ss
        ss -- 클래스_라이브러리 추가 --> auto
        auto -- 자동 구성 후보 로딩 --> cond
    end

    subgraph "no.2"
        direction TB
        default["디폴트 자동 구성 인프라 빈"]
        prop["프로퍼티 소스\napplication.properties"]
        infra["자동 구성 인프라스특러처 빈"]
        cond -- 매칭 조건 판별 --> default
        default --> prop
        prop -- 외부 설정 프로퍼티 적용 --> infra
    end

    subgraph "no.3"
        direction TB
        scan["@ComponentScan"]
        appLogic["@Component\n애플리케이션 로직 빈"]
        conf["@Configuration\n커스텀 인프라 빈"]
        addInfra["@Configuration\n추가 인프라 빈"]
        userConf["유저 구성 애플리케이션 빈"]
        scan -- 자동 빈 등록 --> appLogic
        appLogic --> conf
        conf -. 커스텀 빈으로 대체 .-> infra
        conf --> addInfra
        addInfra --> userConf
    end
```

사용 기술 선택: AWS 위에서 동작할것인지, Redis를 사용할것인지, Jetty 를 사용할것인지 등등

Spring Initializr: 프로젝트 생성

Springboot Starter + Dependency: 의존 라이브러리 및 클래스 추가

@AutoConfiguration AutoConfiguration.imports: 자동 구성 정보 로딩(144가지의 Springboot 가 기본적으로 제공하는 코어 자동 구성
로딩)

@Conditional: 어떤 조건으로 어떤 구성 정보를 로딩하는지 판별

자동구성 인프라스트럭처 빈 + 유저 구성 애플리케이션 빈 = SpringBoot 가 하나의 애플리케이션 구성 정보를 생성

### Springboot 의 자동 구성으로 어떤게 적용되어있나?

spring-boot-starter 에 기본적으로 포함되어있는 자동 구성 정보 빈

```mermaid
block-beta
    columns 2
    debug["-Ddebug, --debug\n(자동 구성 클래스 Condition 결과 로그)"] sbr["SpringBoot Reference\n(문서에서 관련 기술, 자동 구성 프로퍼티 확인 )"]
    cer["ConditionEvaluationReport\n(자동 구성 클래스 Condition 결과 빈)"] a["@AutoConfiguration\n@Conditional\nCondition\n@Bean\n(자동 구성 클래스와 조건, 빈 확인)"]
    lbf["ListableBeanFactory\n(등록된 빈 확인)"] p["Properties\nBind\nCustomizer\nConfigure\n(프로퍼티 클래스와 바인딩)"]
```

</div>
