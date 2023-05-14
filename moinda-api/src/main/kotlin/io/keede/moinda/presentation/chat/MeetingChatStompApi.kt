package io.keede.moinda.presentation.chat

import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * STOMP 콜백함수 API
 * @author keede
 * Created on 2023-05-14
 */
@RestController
class MeetingChatStompApi(
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

    // NOTE : 구독자들에게 접속알림 메세지를 전달할 때 사용,
    @MessageMapping("/join")
    @SendTo("/topic/public")
    fun addUser(@Payload meetingChatEventSubscriber: MeetingChatEventSubscriber,
                headerAccessor: SimpMessageHeaderAccessor): MeetingChatEventSubscriber? {
        headerAccessor.sessionAttributes!!["username"] = meetingChatEventSubscriber.writer
        return meetingChatEventSubscriber
    }

}