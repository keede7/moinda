package io.keede.moinda.domains.meeting.usecase

import io.keede.moinda.domains.meeting.domain.Meeting

/**
 * @author keede
 * Created on 2023-04-19
 */
interface MeetingQueryUseCase {

    fun getById(query: Query) : Meeting

    fun getMeetings() : List<Meeting>

    fun getInParticipatingMeetingsByMemberId(memberId: Long): List<Meeting>

    data class Query(
        val meetingId: Long
    )

}