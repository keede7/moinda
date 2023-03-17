tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":moinda-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

