package io.keede.moinda.presentation.chat

import com.fasterxml.jackson.annotation.JsonFormat
import io.keede.moinda.common.chat.CreateMeetingChat
import io.keede.moinda.domains.chat.domain.MeetingChat
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-10
 */

data class CreateMeetingChatDto(
    val context: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
    val writeAt: LocalDateTime,
    val meetingId: Long,
    val memberId: Long,
)

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
