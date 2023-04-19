package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@UseCaseTest
internal class MeetingQueryTest {

    @MockK
    private lateinit var meetingQueryAdapter: MeetingQueryAdapter

    private lateinit var sut: MeetingQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingQuery(this.meetingQueryAdapter)
    }

    @Test
    fun 특정_모임_조회를_성공한다() {

        val query = mockk<MeetingQueryUseCase.Query>(relaxed = true)

        every { meetingQueryAdapter.findById(any()) } returns mockk(relaxed = true)

        this.sut.getById(query)

        verify(exactly = 1) { meetingQueryAdapter.findById(query.meetingId) }

    }

    @Test
    fun 전체_모임_조회를_성공한다() {

        every { meetingQueryAdapter.findMeetings() } returns mockk(relaxed = true)

        this.sut.getMeetings()

        verify(exactly = 1) { meetingQueryAdapter.findMeetings() }

    }

}