package io.keede.moinda.presentation.config.session

import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

/**
 * @author keede
 * Created on 2023-04-15
 */
class SessionUserResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.hasParameterAnnotation(SessionUser::class.java)
        val isMatchParameterType = parameter.parameterType == SessionResponse::class.java

        return hasAnnotation && isMatchParameterType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)

        val cookies = request?.cookies ?: return null

        return cookies.filter { it.name == Constants.COOKIE_NAME }[0]
            .let {
                request
                    .getSession(false)
                    .getAttribute(it.value)
            }
    }
}