package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
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

    override fun getMeetings(): List<Meeting> {
        val entities = meetingQueryAdapter.findMeetings()

        return entities
            .map { Meeting(it) }
            .toList()
    }

    override fun getInParticipatingMeetingsByMemberId(memberId: Long): List<Meeting> {
        val memberJpaEntity = memberQueryAdapter.findById(memberId)
        val meetingJpaEntity = memberJpaEntity.meetingJpaEntity

        // TODO : 조회를 할 시점에 특정 사용자가 현재 참여하고 있는 모임이 없을 수 있다.
        return meetingJpaEntity?.let{
            listOf(Meeting(it))
        } ?: emptyList()
    }
}