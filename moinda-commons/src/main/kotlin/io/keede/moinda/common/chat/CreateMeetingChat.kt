package io.keede.moinda.common.chat

import java.time.LocalDateTime


/**
 * @author keede
 * Created on 2023-05-09
 */
data class CreateMeetingChat(
    val context: String,
    val writeAt: LocalDateTime,
    val meetingId: Long,
    val memberId: Long,
)