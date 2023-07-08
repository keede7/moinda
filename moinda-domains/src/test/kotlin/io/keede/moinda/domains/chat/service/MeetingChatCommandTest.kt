package io.keede.moinda.domains.chat.service

import io.keede.moinda.core.model.chat.adapter.MeetingChatCommandAdapter
import io.keede.moinda.core.model.meeting.port.MeetingQueryPort
import io.keede.moinda.core.model.member.port.MemberQueryPort
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import io.keede.moinda.domains.config.UseCaseTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-05-10
 */
@UseCaseTest
internal class MeetingChatCommandTest {

    @MockK
    private lateinit var meetingChatCommandAdapter: MeetingChatCommandAdapter

    @MockK
    private lateinit var memberQueryPort: MemberQueryPort

    @MockK
    private lateinit var meetingQueryPort: MeetingQueryPort

    private lateinit var sut: MeetingChatCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingChatCommand(
            this.meetingChatCommandAdapter,
            this.memberQueryPort,
            this.meetingQueryPort
        )
    }

    @Test
    fun 채팅_작성을_성공한다() {

        val command = mockk<MeetingChatCommandUseCase.Command>(relaxed = true)

        every { meetingChatCommandAdapter.save(command.createMeetingChat) } returns mockk(relaxed = true)
        every { memberQueryPort.findById(command.createMeetingChat.memberId) } returns mockk(relaxed = true)
        every { meetingQueryPort.findById(command.createMeetingChat.meetingId) } returns mockk(relaxed = true)

        this.sut.create(command)

        verify(exactly = 1) { meetingChatCommandAdapter.save(command.createMeetingChat) }
        verify(exactly = 1) { memberQueryPort.findById(command.createMeetingChat.memberId) }
        verify(exactly = 1) { meetingQueryPort.findById(command.createMeetingChat.meetingId) }

    }


}