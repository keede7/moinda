package io.keede.moinda.presentation.chat

import com.fasterxml.jackson.annotation.JsonFormat
import io.keede.moinda.common.chat.CreateMeetingChat
import io.keede.moinda.domains.chat.domain.MeetingChat
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

/**
 * @author keede
 * Created on 2023-05-10
 */
data class CreateMeetingChatDto(
    // TODO : Validation 설정 필요.
    val context: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
    val writeAt: LocalDateTime,
    val meetingId: Long,
    val memberId: Long,
)

// 기존에 설계된 변수명에 맞춰서 사용.
data class MeetingChatEventSubscriber(
    val writer: String?,
    val status: MessageType,
)

enum class MessageType {
    CHAT,
    JOIN,
    LEAVE
}

data class MeetingChatResponseDto(
    val chattingId: Long?,
    val writer: String?,
    val context: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
    val writeAt: LocalDateTime,
)

internal fun CreateMeetingChatDto.toDomain() = CreateMeetingChat(
    context = this.context,
    writeAt = this.writeAt,
    meetingId = this.meetingId,
    memberId = this.memberId,
)

internal fun MeetingChat.toMeetingChatResponseDto() = MeetingChatResponseDto(
    chattingId = this.chattingId,
    writer = this.writer,
    context = this.context,
    writeAt = this.writeAt,
)
