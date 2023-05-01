package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.meeting.entity.MeetingProjection
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.keede.moinda.mapper.PaginatedDomain
import io.keede.moinda.mapper.Paginator
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-04-19
 */
@Service
@Transactional(readOnly = true)
internal class MeetingQuery(
    private val meetingQueryAdapter: MeetingQueryAdapter,
    private val memberQueryAdapter: MemberQueryAdapter,
) : MeetingQueryUseCase {

    override fun getById(query: MeetingQueryUseCase.Query): Meeting {
        val meetingJpaEntity = meetingQueryAdapter.findById(query.meetingId)

        return Meeting(meetingJpaEntity)
    }

    // 페이징 처리 없이 모임의 전체 목록을 표시한다.
    override fun getMeetings(): List<Meeting> {
        val entities = meetingQueryAdapter.findMeetings()

        return entities
            .map { Meeting(it) }
            .toList()
    }

    // 모임의 전체 목록을 표시할떄 사용한다. ( 페이징 처리 )
    override fun getMeetings(pageQuery: MeetingQueryUseCase.PageQuery) : Paginator<Meeting> {
        val entitiesByPaging: Page<MeetingProjection> = meetingQueryAdapter.findMeetingByPaging(pageQuery.ofPageable())

//        val paginator: Paginator<Meeting> = PaginatedDomain(findMeetingByPaging, { Meeting(it) } )
        // 보통 인자로 받을 때는 (..) 안에 선언하는데 조금 특이하다. Kotlin의 고유 문법인듯하다.
        val paginator: Paginator<Meeting> = PaginatedDomain(entitiesByPaging) { Meeting(it) }

        return paginator
    }

    // 내 모임을 조회할 떄 사용한다
    override fun getInParticipatingMeetingsByMemberId(memberId: Long): List<Meeting> {
        val memberJpaEntity = memberQueryAdapter.findWithFetch(memberId)
        val meetingJpaEntity = memberJpaEntity.meetingJpaEntity

        // TODO : 조회를 할 시점에 특정 사용자가 현재 참여하고 있는 모임이 없을 수 있다.
        return meetingJpaEntity?.let{
            listOf(Meeting(it))
        } ?: emptyList()
    }
}