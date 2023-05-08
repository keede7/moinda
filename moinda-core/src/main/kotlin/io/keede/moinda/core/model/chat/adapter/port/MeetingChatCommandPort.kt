package io.keede.moinda.core.model.chat.adapter.port

import io.keede.moinda.core.model.chat.adapter.MeetingChatCommandAdapter
import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity
import io.keede.moinda.core.model.chat.entity.MeetingChatJpaRepository

/**
 * @author keede
 * Created on 2023-05-08
 */
internal class MeetingChatCommandPort(
    private val meetingChatJpaRepository: MeetingChatJpaRepository,
) : MeetingChatCommandAdapter {

    override fun save(): MeetingChatJpaEntity {
        TODO("Not yet implemented")
    }
}