# Moinda Kotlin Ver.

### 작업 사항
- [x] 멀티 모듈 설정
- [x] 개발 환경 분리
- [x] Docker 환경 설정
- [x] 도메인 계층 분리

### 멀티 모듈 설정
- **api** : `Presentation` 모듈
- **core** : `Infrastructure` 모듈
- **domain** : `Domain` 모듈
- **commons** : `commons` 모듈

### 이외의 모듈 패키지

- docker : `docker` 와 관련된 설정

### Git Branch 

| branch   | 목적                   |
|----------|----------------------|
| `dev`    | 기능을 개발하는 용도로 사용.     |
| `master` | 운영서버에 반영 하기 전 (`QA`) |
| `prod`   | 실제 운영서버로 사용          |

### Git Commit 전략

| commit     | 목적                                |
|------------|-----------------------------------|
| `feat`     | 새로운 기능 개발                         |
| `refactor` | 기능변화 없이 코드 구조의 변경                 |
| `fix`      | 기능변화에 대한 코드 수정                    |
| `test`     | 테스트 코드 관련                         |
| `chore`    | 이외 패키지 관련 (`문서`, `build`, `환경설정`) |

### 기술 스택
- Kotlin
- Spring Boot
- Querydsl
- Thymeleaf
- Htmx

---

### 선택 이유

#### Kotlin

사실 `Kotlin` 보다는 `Java` 로 개발을 하는 것이 익숙합니다.

하지만, 연습이라는 명목적인 이유가 존재하며, **웹으로 배포하는 것 뿐만 아니라 앱으로 배포하는 부분**에 대한

욕심도 조금 있기 때문에, 그와 비슷한 환경을 가질 수 있는 언어인 `Kotlin` 으로 선정했습니다.

#### Thymeleaf, Htmx

`Thymeleaf` 는 **Page Rendering**, `Htmx` 는 **Data**를 전달하는 역할로 사용할 것입니다.

`MPA` 가 아닌 `SPA` 방식으로의 개발은 기본적으로 러닝커브가 존재하기 떄문에, 조금 더 효율적으로 사용하고자

`Htmx` 를 사용하는 방향으로 선택했습니다.
