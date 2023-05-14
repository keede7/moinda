package io.keede.moinda.presentation.chat

import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatQueryUseCase
import io.keede.moinda.util.toResponseDtoList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author keede
 * Created on 2023-05-10
 */
@RestController
@RequestMapping("/api/chat")
class MeetingChatRestController(
    private val meetingChatQueryUseCase: MeetingChatQueryUseCase
) {

    @GetMapping("/message/{meetingId}")
    fun getList(
        @PathVariable meetingId: Long
    ): List<MeetingChatResponseDto> =
        meetingChatQueryUseCase.getChatting(meetingId)
            .toResponseDtoList(MeetingChat::toMeetingChatResponseDto)

}