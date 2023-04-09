/**
 * https://sabarada.tistory.com/182
 * Kotlin에서 지원해주지 않는 기능들이 존재하는데, 그 기능들을 추가.
 */
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    implementation(project(":moinda-commons"))
    // Querydsl 관련 라이브러리
    implementation("com.querydsl:querydsl-jpa")
    // TODO : 적용해야하는 부분.
    kapt("com.querydsl:querydsl-apt")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
}