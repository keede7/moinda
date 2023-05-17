package io.keede.moinda.core.model.meeting.adapter.port

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.meeting.adapter.MeetingBatchAdapter
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.QMeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.QMeetingJpaEntity.meetingJpaEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-17
 */
@Service
@Transactional
internal class MeetingBatchPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MeetingBatchAdapter, QuerydslRepositorySupport(QMeetingJpaEntity::class.java) {

    override fun findExpiredMeetings(today: LocalDateTime): List<MeetingJpaEntity> {
        return jpaQueryFactory
            .selectFrom(meetingJpaEntity)
            .where(
                meetingJpaEntity.deleteStatus.isFalse
                    .and(meetingJpaEntity.endAt.before(today))
            )
            .fetch()
    }
}