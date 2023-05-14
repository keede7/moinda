package io.keede.moinda.presentation.config.websocket

import io.keede.moinda.presentation.chat.MeetingChatEventSubscriber
import io.keede.moinda.presentation.chat.MessageType
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

/**
 * @author keede
 * Created on 2023-05-10
 */
@Component
class WebSocketEventListener(
    private val messagingTemplate: SimpMessageSendingOperations?
) {

    // NOTE : 입장시 로그찍을떄 사용
//    @EventListener
//    fun handleWebSocketConnectListener(event: SessionConnectedEvent?) {
//        println("Received a new web socket connection")
//    }

    // NOTE: 연결이 끊겼을 경우 구독자에게 알림메세지를 전달.
    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val username = headerAccessor.sessionAttributes!!["username"] as String?
        if (username != null) {
            println("User Disconnected : $username")
            val meetingChatEventSubscriber = MeetingChatEventSubscriber(username, MessageType.LEAVE)

            messagingTemplate!!.convertAndSend("/topic/public", meetingChatEventSubscriber)
        }
    }
}