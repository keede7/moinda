package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.port.MeetingQueryPort
import io.keede.moinda.core.model.member.port.MemberQueryPort
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
    private lateinit var meetingQueryPort: MeetingQueryPort

    @MockK
    private lateinit var memberQueryPort: MemberQueryPort

    private lateinit var sut: MeetingQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingQuery(
            this.meetingQueryPort,
            this.memberQueryPort,
        )
    }

    @Test
    fun 특정_모임_조회를_성공한다() {

        val query = mockk<MeetingQueryUseCase.Query>(relaxed = true)

        every { meetingQueryPort.findById(any()) } returns mockk(relaxed = true)

        this.sut.getById(query)

        verify(exactly = 1) { meetingQueryPort.findById(query.meetingId) }

    }

    @Test
    fun 특정_사용자가_참여중인_조회를_성공한다() {

        val memberId = 1L

        every { memberQueryPort.findWithFetch(any())} returns mockk(relaxed = true)

        this.sut.getInParticipatingMeetingsByMemberId(memberId)

        verify(exactly = 1) { memberQueryPort.findWithFetch(memberId) }

    }

    @Test
    fun 페이징된_전체_모임_조회를_성공한다() {

        val pageQuery = mockk<MeetingQueryUseCase.PageQuery>(relaxed = true)

        every { meetingQueryPort.findMeetingByPaging(pageQuery.ofPageable()) } returns mockk(relaxed = true)

        this.sut.getMeetings(pageQuery)

        verify { meetingQueryPort.findMeetingByPaging(pageQuery.ofPageable()) }

    }

}