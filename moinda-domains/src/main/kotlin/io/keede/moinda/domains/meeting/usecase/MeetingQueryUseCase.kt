package io.keede.moinda.domains.meeting.usecase

import io.keede.moinda.domains.meeting.domain.Meeting

/**
 * @author keede
 * Created on 2023-04-19
 */
interface MeetingQueryUseCase {

    fun getById(meetingId: Long) : Meeting

    fun getMeetings() : List<Meeting>

}