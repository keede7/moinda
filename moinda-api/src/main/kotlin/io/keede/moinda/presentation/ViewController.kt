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

    @GetMapping("/sample")
    fun maps2(): String = "sample/thema-sample"

    @GetMapping("/")
    fun index(): String = "index"

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