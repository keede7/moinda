package io.keede.moinda.presentation.chat

import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author keede
 * Created on 2023-05-10
 */
@RestController
@RequestMapping("/api/chat")
class MeetingChatRestController(
    private val meetingChatCommandUseCase: MeetingChatCommandUseCase
) {

    @PostMapping("/send")
    fun create(
        @RequestBody createMeetingChatDto: CreateMeetingChatDto
    ) : MeetingChatResponseDto =
        meetingChatCommandUseCase.create(
            MeetingChatCommandUseCase.Command(
                createMeetingChatDto.toDomain()
            )
        ).let(MeetingChat::toMeetingChatResponseDto)

}