package io.keede.moinda.presentation.websocket

/**
 * @author keede
 * Created on 2023-05-09
 */
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