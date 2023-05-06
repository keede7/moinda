package io.keede.moinda.presentation

import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author keede
 * Created on 2023-04-17
 */
@Controller
class ViewController {

    // TODO : 테스트 세션
    private fun createSession(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val session = request.getSession(true)

        val toSessionResponse = SessionResponse(1, "테스터")

        session.setAttribute(Constants.SESSION_KEY, toSessionResponse)
        session.maxInactiveInterval = Constants.MAX_IN_ACTIVE_INTERVAL

        Cookie(Constants.COOKIE_NAME, Constants.SESSION_KEY)
            .also {
                it.maxAge = Constants.MAX_IN_ACTIVE_INTERVAL
                it.path = "/"
                response.addCookie(it)
            }
    }

    @GetMapping("/sample")
    fun maps2(): String = "sample/thema-sample"

    // TODO : Bootstrap Example
    @GetMapping("/")
    fun index(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): String {
        createSession(request, response)

        return "index"
    }

    // TODO : kakao Map Test
    @GetMapping("/maps")
    fun maps(): String = "sample/maps"

    @GetMapping("/signup")
    fun signup(): String = "signup"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/meetings")
    fun meetings(): String = "meeting/meetings"

    @GetMapping("/meeting/register")
    fun meetingRegister(): String = "meeting/register"

    @GetMapping("/myMeetings")
    fun myMeetings(): String = "meeting/myMeetings"

    @GetMapping("meeting-detail/{meetingId}")
    fun meetingDetail(
        @PathVariable meetingId: Long
    ): String = "meeting/meetingDetail"

}