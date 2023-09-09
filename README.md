# Moinda Kotlin Ver.

### 작업 사항
- [Moinda 프로젝트 기획정리](https://jumpy-cylinder-eb2.notion.site/Moinda-Mo-Gak-Ko-bbf965c56d094a9aabe44810025c1fc9)

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
- `Kotlin`
- `Spring Boot 2.7.x`
- `Querydsl` => `JooQ` ( 변경 예정 by 8.2 ) [참고자료](https://github.com/etiennestuder/gradle-jooq-plugin)
- `Thymeleaf`

---


