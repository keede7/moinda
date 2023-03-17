package io.kopring.boilerplate.core.config

import io.kopring.boilerplate.core.Core
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
// TODO : 필요없는지 확인 필요
//@EnableJpaRepositories(basePackageClasses = [Core::class])
//@EnableJpaRepositories
class JpaConfig