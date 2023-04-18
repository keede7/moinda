package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingCommandAdapter
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
    private val meetingCommandAdapter: MeetingCommandAdapter
) : MeetingCommandUseCase {
    override fun create(
        command: MeetingCommandUseCase.Command
    ): Meeting =
        meetingCommandAdapter.save(command.createMeeting)
            .let(::Meeting)
}