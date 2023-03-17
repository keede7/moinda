# Spring-kotlin-boilerplate

### 작업 사항
- [x] 멀티 모듈 설정
- [x] 개발 환경 분리
- [x] Docker 환경 설정
- [ ] 도메인 계층 분리

### 멀티 모듈 설정
- api : `Presentation` 모듈
- core : `Domain` 모듈

### 개발 환경 분리
- local
- dev

#### 이외의 모듈 패키지

- docker : `docker` 와 관련된 설정

### Git Branch 전략

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
