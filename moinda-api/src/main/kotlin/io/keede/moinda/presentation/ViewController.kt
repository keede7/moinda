package io.keede.moinda.presentation

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody


/**
 * @author keede
 * Created on 2023-04-17
 */
@Controller
class ViewController {
    @GetMapping("/")
    fun main(): String = "main"

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