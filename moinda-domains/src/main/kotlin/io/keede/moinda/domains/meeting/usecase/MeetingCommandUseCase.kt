package io.keede.moinda.domains.meeting.usecase

import io.keede.moinda.common.meeting.CreateMeeting
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.meeting.domain.Meeting


/**
 * @author keede
 * Created on 2023-04-18
 */
interface MeetingCommandUseCase {

    fun create(session: SessionResponse, command: Command): Meeting

    data class Command(
        val createMeeting: CreateMeeting
    )
}