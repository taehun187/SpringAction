# SpringinAction 책을 통해 학습했습니다

![image](https://github.com/user-attachments/assets/7fc7c1bc-15d1-4e0b-9275-b1e07bea1140)


# **샘플 코드 업그레이드 세부사항**

### **Spring Boot 3 버전 업그레이드**
- 프로젝트를 최신버전인 Spring Boot 3 버전으로 업그레이드하였습니다.


### **TacoCloud UI 업그레이드**
- `tacocloud-ui`가 최신 버전으로 업데이트되었습니다.
- 새로운 UI 컴포넌트와 스타일이 추가되어 사용자 경험이 개선되었습니다.

# Taco Cloud v0.0.6

이 폴더는 **Spring in Action, 5th edition**의 Chapter 6에서 소개된 Taco Cloud 샘플 코드의 소스 코드를 포함하고 있습니다.

응용 프로그램의 일부는 아직 완전히 작동하지 않을 수 있지만, Chapter 6과 관련된 코드는 완전하며 실행할 준비가 되어 있습니다.

또한, 예제 코드는 챕터를 진행하면서 점진적으로 진화할 수 있습니다. 하나의 코드 조각에는 여러 가지 변형 또는 진화된 형태가 있을 수 있으며, 여기 제공된 샘플 코드는 그러한 변형 중 하나를 나타낼 수 있습니다.

---

## Taco Cloud 실행 방법

Chapter 6부터 Taco Cloud는 다중 모듈 Maven 프로젝트로 나누어져 있습니다. 전체 프로젝트를 빌드하려면 명령줄에서 Maven Wrapper(`mvnw`)를 사용하세요:

```sh
% ./mvnw clean package
```

Maven 빌드는 Angular 클라이언트 코드를 빌드하고 Spring 코드와 함께 패키징합니다. 이를 위해 프로젝트를 빌드하기 전에 Node와 Angular CLI가 설치되어 있어야 합니다. 

프로젝트 빌드가 완료되면 `tacos` 모듈의 실행 가능한 JAR 파일을 실행할 수 있습니다:

```sh
% java -jar tacos/target/taco-cloud-0.0.6-SNAPSHOT.jar
```

응용 프로그램이 시작되면 웹 브라우저에서 [http://localhost:8080](http://localhost:8080)에 접속하여 Taco Cloud의 홈 페이지를 확인할 수 있습니다.

---

## 애플리케이션 실행해보기

위에서 언급했듯이 Taco Cloud 애플리케이션의 모든 기능이 아직 완전히 구현된 것은 아닙니다. 그러나 Chapter 6에서 다루는 REST API와 관련된 구성 요소는 준비되어 있습니다.

홈 페이지에서 상단의 "Latest designs" 링크를 클릭하여 최근에 생성된 타코 디자인을 볼 수 있습니다. "Specials"와 "Locations" 링크는 해당 페이지로 이동하지만, 현재로서는 빈 페이지입니다.

"DESIGN A TACO" 링크를 클릭하여 타코를 디자인하고 주문을 생성할 수 있습니다. 오른쪽 상단의 장바구니/가격을 클릭하면 쇼핑 카트 내용을 확인할 수 있습니다.

---

### 아직 구현되지 않았거나 작동하지 않는 기능

현재 수정이 필요한 주요 사항은 다음과 같습니다:

- **보안 기능**: 로그인 페이지가 작동하지 않습니다.
- **회원가입 기능**: 사용자 등록 방법이 아직 구현되지 않았습니다.

이 기능들은 앞으로 구현될 예정이지만, Chapter 6의 주제를 설명하는 코드 작성에 우선순위를 두었습니다.

---

## Taco Cloud 모듈 구성

이 다중 모듈 Maven 프로젝트는 다음 모듈로 구성되어 있습니다:

- **`tacocloud-api`**: REST API 모듈.
- **`tacocloud-data`**: 데이터 영속성(persistence) 모듈.
- **`tacocloud-domain`**: 도메인 타입 모듈.
- **`tacocloud-security`**: 보안 모듈 (TODO: 아직 완전히 작동하지 않음).
- **`tacocloud-ui`**: TypeScript 기반 Angular UI 모듈.
- **`tacocloud-web`**: 이전 챕터의 코드가 포함된 웹 모듈 (TODO: 정리 및 제거 예정).
- **`tacos`**: 다른 모듈을 통합하고 Spring Boot 메인 클래스를 제공하는 메인 모듈.
```
