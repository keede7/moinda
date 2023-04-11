/**
 * https://sabarada.tistory.com/182
 * Kotlin에서 지원해주지 않는 기능들이 존재하는데, 그 기능들을 추가.
 */
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir")
}

dependencies {
    implementation(project(":moinda-commons"))
    // Querydsl 관련 라이브러리
    implementation("com.querydsl:querydsl-jpa")
    // apt:{버전}:jpa => `:jpa` 까지 명시를 해줘야 Q도메인이 생성됨.
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
}