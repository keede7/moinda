package io.keede.moinda.core.model.meeting.adapter.port

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.QMeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.QMeetingJpaEntity.meetingJpaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-04-18
 */
@Service
internal class MeetingQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MeetingQueryAdapter, QuerydslRepositorySupport(QMeetingJpaEntity::class.java) {

    override fun findById(meetingId: Long): MeetingJpaEntity {
        return jpaQueryFactory
            .selectFrom(meetingJpaEntity)
            .where(
                meetingJpaEntity.id.eq(meetingId)
                    .and(meetingJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException("존재하지 않는 모임입니다.")
    }

    override fun findMeetings(): List<MeetingJpaEntity> {
        return jpaQueryFactory
            .selectFrom(meetingJpaEntity)
            .where(meetingJpaEntity.deleteStatus.isFalse)
            .fetch()
    }

    override fun findMeetingByPaging(pageable: Pageable): Page<MeetingJpaEntity> {
        val entities = jpaQueryFactory
            .selectFrom(meetingJpaEntity)
            .where(
                meetingJpaEntity.deleteStatus.isFalse
            )

        val count = entities.fetch().size.toLong()

        val limitEntities = entities.offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(meetingJpaEntity.id.desc())
            .fetch()

        return PageImpl(limitEntities, pageable, count)
    }
}