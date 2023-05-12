package io.keede.moinda.config.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


/**
 * @author keede
 * Created on 2023-05-09
 */
/*
※ Stomp
    Stomp는 Websocket 연결 시, 메시지의 형태(텍스트 또는 바이너리)를 HTTP 통신 프로토콜과 비슷하게 가공해주는 라이브러리입니다.
    위 코드가 STOMP를 사용한 Websocket 설정이고, 기존에 Websocket을 설정에 비해 간소하고 쉽게 사용할 수 있게 도와줍니다.
    WebSocketConfigurer 인터페이스의 고수준 API를 사용케 해줍니다.
    일종의 Websocket 메시지 브로커. (spring-boot-starter-websocket 에 포함됨)
 */
@Configuration
// WebSocket을 통한 메시징 기능을 활성화 시킵니다.
@EnableWebSocketMessageBroker
class WebSocketConfig
    : WebSocketMessageBrokerConfigurer {

    // Websocket 연결을 위한 엔드포인트를 지정해줍니다
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws").withSockJS()
    }

    // 메시지 주고 받을 엔드포인트에 대한 Prefix를 정해줍니다
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // 서버가 목적지 일때(Client -> Server 메시지 전송시 Endpoint)
        config.setApplicationDestinationPrefixes("/app")
        // 클라이언트가 Subscribe 할떄(Server -> Client 메시지 전송 시 Endpoint)
        config.enableSimpleBroker("/topic")
    }

}