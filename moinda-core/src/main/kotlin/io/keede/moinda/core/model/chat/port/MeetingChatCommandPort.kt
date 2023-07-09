package io.keede.moinda.core.model.chat.port

import io.keede.moinda.common.chat.CreateMeetingChat
import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity

/**
 * @author keede
 * Created on 2023-05-08
 */
interface MeetingChatCommandPort {

    fun save(createMeetingChat: CreateMeetingChat): MeetingChatJpaEntity

}