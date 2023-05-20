package io.keede.moinda.presentation.config.session

import io.keede.moinda.common.member.session.Constants
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author keede
 * Created on 2023-04-15
 */
class SessionUserInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest,
                           response: HttpServletResponse,
                           handler: Any): Boolean {

        val session = request.getSession(false)

        session?.getAttribute(Constants.SESSION_KEY)
            ?: run { throw RuntimeException("세션이 만료되었습니다. 다시 로그인 해주세요.") }

        return true
    }
}