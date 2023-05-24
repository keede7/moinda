package io.keede.moinda

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.web.SecurityFilterChain

/**
 * @author keede
 * Created on 2023-05-20
 */
@EnableWebSecurity
internal class SecurityConfig(
    private val oAuth2Service: CustomOAuth2Service
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        http
            .httpBasic().disable()
            .csrf().disable() // html 방식을 쓰지 않으므로 csrf를 고려할 필요성 X
            .formLogin().disable() // Rest Api이므로 Form 방식 X

            .authorizeHttpRequests {
                it
                    .antMatchers(
                        "/api/**"
                    )
                    .authenticated()
            }

            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(this.oAuth2Service)

        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        // 정적 리소스 spring security 대상에서 제외
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        }
    }
}