package io.keede.moinda.presentation.config

import io.keede.moinda.presentation.config.session.SessionUserInterceptor
import io.keede.moinda.presentation.config.session.SessionUserResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author keede
 * Created on 2023-04-15
 */
@Configuration
class WebConfig : WebMvcConfigurer {

    private val ALLOW_PATH = "/api/**"
    private val EXCLUDE_PATH = listOf(
        "/api/member/login",
        "/api/member/signup",
        "/api/meeting/**"
    )

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(SessionUserResolver())
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(SessionUserInterceptor())
            .excludePathPatterns(
                EXCLUDE_PATH
            )
            .addPathPatterns(
                ALLOW_PATH
            )
    }
}