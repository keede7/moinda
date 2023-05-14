package io.keede.moinda.domains.chat.domain

import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity
import io.keede.moinda.core.model.chat.entity.MeetingChatProjection
import io.keede.moinda.util.toFullPattern
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-09
 */
data class MeetingChat(
    val chattingId: Long?,
    val writer: String?,
    val context: String,
    val writeAt: String,
) {

    constructor(meetingChatJpaEntity: MeetingChatJpaEntity) : this(
        chattingId = meetingChatJpaEntity.id,
        writer = meetingChatJpaEntity.memberJpaEntity?.name,
        context = meetingChatJpaEntity.context,
        writeAt = meetingChatJpaEntity.writeAt.toFullPattern(),
    )

    constructor(meetingChatProjection: MeetingChatProjection) : this(
        chattingId = meetingChatProjection.chattingId,
        writer = meetingChatProjection.writer,
        context = meetingChatProjection.context,
        writeAt = meetingChatProjection.writeAt,
    )

}