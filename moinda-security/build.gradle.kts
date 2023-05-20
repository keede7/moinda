dependencies {
    // TODO : Core가 아니라 Domain Layer를 의존하는 것으로
    implementation(project(":moinda-core"))
    implementation(project(":moinda-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
}
