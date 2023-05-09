package io.keede.moinda.domains.chat.service

import io.keede.moinda.core.model.chat.adapter.MeetingChatCommandAdapter
import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-05-09
 */
@Service
class MeetingChatCommand(
    private val meetingChatCommandAdapter: MeetingChatCommandAdapter,
    private val memberQueryAdapter: MemberQueryAdapter,
    private val meetingQueryAdapter: MeetingQueryAdapter,
) : MeetingChatCommandUseCase {

    override fun create(command: MeetingChatCommandUseCase.Command): MeetingChat {
        val createMeetingChat = command.createMeetingChat
        val meetingChatJpaEntity = meetingChatCommandAdapter.save(createMeetingChat)

        val memberJpaEntity = memberQueryAdapter.findById(createMeetingChat.memberId)
        val meetingJpaEntity = meetingQueryAdapter.findById(createMeetingChat.meetingId)

        meetingChatJpaEntity.initMember(memberJpaEntity)
        meetingChatJpaEntity.initMeeting(meetingJpaEntity)

        return MeetingChat(meetingChatJpaEntity)
    }
}