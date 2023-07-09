package io.keede.moinda.core.model.meeting.port.adapter

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.meeting.port.MeetingBatchPort
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
internal class MeetingBatchAdapter(
    private val jpaQueryFactory: JPAQueryFactory
) : MeetingBatchPort, QuerydslRepositorySupport(QMeetingJpaEntity::class.java) {

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