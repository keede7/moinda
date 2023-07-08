package io.keede.moinda.core.model.chat.port.adapter

import io.keede.moinda.common.chat.CreateMeetingChat
import io.keede.moinda.core.model.chat.port.MeetingChatCommandPort
import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity
import io.keede.moinda.core.model.chat.entity.MeetingChatJpaRepository
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-05-08
 */
@Service
internal class MeetingChatCommandAdapter(
    private val meetingChatJpaRepository: MeetingChatJpaRepository,
) : MeetingChatCommandPort {

    override fun save(createMeetingChat: CreateMeetingChat): MeetingChatJpaEntity {
        val entity = meetingChatJpaRepository.save(
            MeetingChatJpaEntity(
                createMeetingChat.context,
                createMeetingChat.writeAt,
            )
        )

        return meetingChatJpaRepository.save(entity)
    }
}