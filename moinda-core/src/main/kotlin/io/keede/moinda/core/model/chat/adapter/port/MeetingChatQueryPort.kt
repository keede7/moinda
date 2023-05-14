package io.keede.moinda.core.model.chat.adapter.port

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.chat.adapter.MeetingChatQueryAdapter
import io.keede.moinda.core.model.chat.entity.MeetingChatProjection
import io.keede.moinda.core.model.chat.entity.QMeetingChatJpaEntity
import io.keede.moinda.core.model.chat.entity.QMeetingChatJpaEntity.meetingChatJpaEntity
import io.keede.moinda.core.model.chat.entity.QMeetingChatProjection
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-05-08
 */
@Service
internal class MeetingChatQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MeetingChatQueryAdapter, QuerydslRepositorySupport(QMeetingChatJpaEntity::class.java) {

    // TODO : 메세지들을 보여줄 때의 설계가 추가적으로 필요함, EX) 스크롤링으로 추가요청 , 캐시전략 사용, 페이징처리
    override fun findChatsByMeetingId(meetingId: Long): List<MeetingChatProjection> {
        return jpaQueryFactory.select(
            QMeetingChatProjection(
                meetingChatJpaEntity.id,
                meetingChatJpaEntity.memberJpaEntity.name,
                meetingChatJpaEntity.context,
                meetingChatJpaEntity.writeAt.stringValue()
            )
        )
            .from(meetingChatJpaEntity)
            .where(
                meetingChatJpaEntity.meetingJpaEntity.id.eq(meetingId)
                    .and(meetingChatJpaEntity.deleteStatus.isFalse)
            )
            .fetch()
    }

}