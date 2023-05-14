package io.keede.moinda.presentation.config.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


/**
 * TODO : 추후 삭제
 * @author keede
 * Created on 2023-05-09
 */
/*
클라이언트에서 서버로 메시지를 보내는 엔드 포인트를 /chat.sendMessage 로 정의했으며,
동시에 /topic/public 를 통해 구독(subscribe)중인 클라이언트 앱에 ChatMessage 데이터를 발행(publish)합니다.
 */
@Controller
class ChatController {

    @GetMapping("/chat")
    fun chat() : String {
        return "chat"
    }

    // 서버에서는 MessageMapping에 따라서 값을 받고
    // 그 값으로 바인딩한 결과들을 구독중인 클라이언트에 전달한다 ( /topic/public ) 을 구독하고 있는 사용자들에게,
    @ResponseBody
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage?): ChatMessage? {
        return chatMessage
    }

    // /chat.addUser는 채팅방에 유저가 접속할 때 실행되는 메서드이며, Session에 유저를 추가해줍니다.
    @ResponseBody
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chatMessage: ChatMessage, headerAccessor: SimpMessageHeaderAccessor): ChatMessage? {
        headerAccessor.sessionAttributes!!["username"] = chatMessage.sender
        return chatMessage
    }

    data class ChatMessage(
        var type: MessageType,
        var content: String?,
        var sender: String
    )

    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}