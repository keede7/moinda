package io.keede.moinda.core.model.chat.adapter

import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity

/**
 * @author keede
 * Created on 2023-05-08
 */
interface MeetingChatQueryAdapter {

    // 특정 메세지를 보여주는 게 아니라, 해당 모임의 채팅목록 전체를 보여주는 것이 낫다.
    fun findChatsByMeetingId(meetingId: Long) : List<MeetingChatJpaEntity>

}