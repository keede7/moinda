package io.keede.moinda.domains.chat.service

import io.keede.moinda.core.model.chat.adapter.MeetingChatCommandAdapter
import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
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
    private lateinit var memberQueryAdapter: MemberQueryAdapter

    @MockK
    private lateinit var meetingQueryAdapter: MeetingQueryAdapter

    private lateinit var sut: MeetingChatCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingChatCommand(
            this.meetingChatCommandAdapter,
            this.memberQueryAdapter,
            this.meetingQueryAdapter
        )
    }

    @Test
    fun 채팅_작성을_성공한다() {

        val command = mockk<MeetingChatCommandUseCase.Command>(relaxed = true)

        every { meetingChatCommandAdapter.save(command.createMeetingChat) } returns mockk(relaxed = true)
        every { memberQueryAdapter.findById(command.createMeetingChat.memberId) } returns mockk(relaxed = true)
        every { meetingQueryAdapter.findById(command.createMeetingChat.meetingId) } returns mockk(relaxed = true)

        this.sut.create(command)

        verify(exactly = 1) { meetingChatCommandAdapter.save(command.createMeetingChat) }
        verify(exactly = 1) { memberQueryAdapter.findById(command.createMeetingChat.memberId) }
        verify(exactly = 1) { meetingQueryAdapter.findById(command.createMeetingChat.meetingId) }

    }


}