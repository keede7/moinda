package io.keede.moinda.presentation.chat

import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author keede
 * Created on 2023-05-10
 */
@RestController
@RequestMapping("/api/chat")
class MeetingChatRestController(
    private val meetingChatCommandUseCase: MeetingChatCommandUseCase
) {

    // NOTE : WebSocket + STOMP를 이용한 처리를 사용중. @MessageMapping의 경로로만 매핑된다.
    @MessageMapping("/send")
    @SendTo("/topic/public")
    fun sendMessage(@Payload @RequestBody @Valid createMeetingChatDto: CreateMeetingChatDto): MeetingChatResponseDto? {
        return meetingChatCommandUseCase.create(
            MeetingChatCommandUseCase.Command(
                createMeetingChatDto.toDomain()
            )
        ).let(MeetingChat::toMeetingChatResponseDto)
    }

}