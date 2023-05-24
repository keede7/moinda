package io.keede.moinda.presentation

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


/**
 * @author keede
 * Created on 2023-04-17
 */
@Controller
class ViewController {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/sample")
    fun maps2(): String = "sample/thema-sample"

    @GetMapping("/")
    fun index(): String = "index"

    // TODO : kakao Map Test
    @GetMapping("/maps")
    fun maps(): String = "sample/maps"

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