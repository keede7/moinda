package io.keede.moinda.core.model.chat.adapter.port

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.chat.adapter.MeetingChatQueryAdapter
import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity
import io.keede.moinda.core.model.chat.entity.QMeetingChatJpaEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

/**
 * @author keede
 * Created on 2023-05-08
 */
class MeetingChatQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MeetingChatQueryAdapter, QuerydslRepositorySupport(QMeetingChatJpaEntity::class.java) {

    override fun findChatsByMeetingId(meetingId: Long): List<MeetingChatJpaEntity> {
        TODO("Not yet implemented")
    }

}