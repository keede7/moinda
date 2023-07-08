package io.keede.moinda.domains.chat.service

import io.keede.moinda.core.model.chat.adapter.MeetingChatCommandAdapter
import io.keede.moinda.core.model.meeting.port.MeetingQueryPort
import io.keede.moinda.core.model.member.port.MemberQueryPort
import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-05-09
 */
@Service
@Transactional
class MeetingChatCommand(
    private val meetingChatCommandAdapter: MeetingChatCommandAdapter,
    private val memberQueryPort: MemberQueryPort,
    private val meetingQueryPort: MeetingQueryPort,
) : MeetingChatCommandUseCase {

    override fun create(command: MeetingChatCommandUseCase.Command): MeetingChat {
        val createMeetingChat = command.createMeetingChat
        val meetingChatJpaEntity = meetingChatCommandAdapter.save(createMeetingChat)

        val memberJpaEntity = memberQueryPort.findById(createMeetingChat.memberId)
        val meetingJpaEntity = meetingQueryPort.findById(createMeetingChat.meetingId)

        meetingChatJpaEntity.initMember(memberJpaEntity)
        meetingChatJpaEntity.initMeeting(meetingJpaEntity)

        return MeetingChat(meetingChatJpaEntity)
    }
}