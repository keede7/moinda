import org.jetbrains.kotlin.ir.backend.js.compile

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":moinda-domains"))
    implementation(project(":moinda-commons"))
    runtimeOnly(project(":moinda-batch"))
    runtimeOnly(project(":moinda-security"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    testImplementation("org.springframework.security:spring-security-test")
    // @MockkBean Dependency
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

