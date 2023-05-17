package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingBatchAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.meeting.usecase.MeetingBatchUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-18
 */
@UseCaseTest
internal class MeetingBatchTest{

    @MockK
    private lateinit var meetingBatchAdapter: MeetingBatchAdapter

    private lateinit var sut: MeetingBatchUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingBatch(this.meetingBatchAdapter)
    }

    @Test
    fun 만료된_모임_삭제_배치_성공() {

        val today = mockk<LocalDateTime>()

        every { meetingBatchAdapter.findExpiredMeetings(any()) } returns mockk(relaxed = true)

        this.sut.cleanUpExpiredMeetings(today)

        verify(exactly = 1) { meetingBatchAdapter.findExpiredMeetings(any()) }

    }

}