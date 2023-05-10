package io.keede.moinda.presentation.chat.fixture

import io.keede.moinda.presentation.chat.CreateMeetingChatDto
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-10
 */

internal fun ofCreateMeetingChatDto(
     context: String,
     writeAt: LocalDateTime,
     meetingId: Long,
     memberId: Long,
) = CreateMeetingChatDto(
    context,
    writeAt,
    meetingId,
    memberId,
)
