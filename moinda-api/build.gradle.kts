tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":moinda-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

