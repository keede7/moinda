package io.keede.moinda.domains.chat.domain

import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-09
 */
data class MeetingChat(
    private val meetingChatJpaEntity: MeetingChatJpaEntity
) {
    val chattingId: Long? = meetingChatJpaEntity.id
    val writer: String? = meetingChatJpaEntity.memberJpaEntity?.name
    val context: String = meetingChatJpaEntity.context
    val writeAt: LocalDateTime = meetingChatJpaEntity.writeAt
}