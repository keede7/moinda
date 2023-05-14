package io.keede.moinda.core.model.chat.entity

import com.querydsl.core.annotations.QueryProjection

/**
 * @author keede
 * Created on 2023-05-14
 */
class MeetingChatProjection @QueryProjection constructor(
    val chattingId: Long,
    val writer: String?,
    val context: String,
    val writeAt: String,
)