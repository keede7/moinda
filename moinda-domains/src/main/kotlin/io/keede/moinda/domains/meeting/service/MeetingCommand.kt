package io.keede.moinda.domains.meeting.service

import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.core.model.meeting.adapter.MeetingCommandAdapter
import io.keede.moinda.core.model.member.port.MemberQueryPort
import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.domains.meeting.usecase.MeetingCommandUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 * @author keede
 * Created on 2023-04-18
 */
@Service
@Transactional
internal class MeetingCommand(
    private val meetingCommandAdapter: MeetingCommandAdapter,
    private val memberQueryPort: MemberQueryPort,
) : MeetingCommandUseCase {
    override fun create(
        session: SessionResponse,
        command: MeetingCommandUseCase.Command,
    ): Meeting {
        val memberJpaEntity = memberQueryPort.findById(session.memberId)
        val meetingJpaEntity = meetingCommandAdapter.save(command.createMeeting)

        memberJpaEntity.participate(meetingJpaEntity)

        return Meeting(meetingJpaEntity)
    }
}