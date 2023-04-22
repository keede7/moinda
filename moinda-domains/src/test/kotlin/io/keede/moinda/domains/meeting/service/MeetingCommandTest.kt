package io.keede.moinda.domains.meeting.service

import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.core.model.meeting.adapter.MeetingCommandAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.meeting.usecase.MeetingCommandUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**
 * @author keede
 * Created on 2023-04-19
 */
@UseCaseTest
internal class MeetingCommandTest {

    @MockK
    private lateinit var meetingCommandAdapter: MeetingCommandAdapter

    @MockK
    private lateinit var memberQueryAdapter: MemberQueryAdapter

    private lateinit var sut: MeetingCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingCommand(
            this.meetingCommandAdapter,
            this.memberQueryAdapter
        )
    }

    @Test
    fun 모임_생성을_성공한다() {

        val command = mockk<MeetingCommandUseCase.Command>()
        val session = mockk<SessionResponse>(relaxed = true)

        every { meetingCommandAdapter.save(command.createMeeting) } returns mockk(relaxed = true)
        every { memberQueryAdapter.findById(session.memberId) } returns mockk(relaxed = true)

        this.sut.create(session, command)

        verify(exactly = 1) { meetingCommandAdapter.save(command.createMeeting) }
        verify(exactly = 1) { memberQueryAdapter.findById(session.memberId) }

    }

}