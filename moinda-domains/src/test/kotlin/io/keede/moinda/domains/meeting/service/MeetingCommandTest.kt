package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingCommandAdapter
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

    private lateinit var sut: MeetingCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingCommand(this.meetingCommandAdapter)
    }

    @Test
    fun 모임_생성을_성공한다() {

        val command = mockk<MeetingCommandUseCase.Command>()

        every { meetingCommandAdapter.save(command.createMeeting) } returns mockk(relaxed = true)

        this.sut.create(command)

        verify(exactly = 1) { meetingCommandAdapter.save(command.createMeeting) }

    }

}