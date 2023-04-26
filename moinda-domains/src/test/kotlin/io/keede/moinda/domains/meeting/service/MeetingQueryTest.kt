package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Pageable

@UseCaseTest
internal class MeetingQueryTest {

    @MockK
    private lateinit var meetingQueryAdapter: MeetingQueryAdapter

    @MockK
    private lateinit var memberQueryAdapter: MemberQueryAdapter

    private lateinit var sut: MeetingQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingQuery(
            this.meetingQueryAdapter,
            this.memberQueryAdapter,
        )
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

    @Test
    fun 특정_사용자가_참여중인_조회를_성공한다() {

        val memberId = 1L

        every { memberQueryAdapter.findById(any())} returns mockk(relaxed = true)

        this.sut.getInParticipatingMeetingsByMemberId(memberId)

        verify(exactly = 1) { memberQueryAdapter.findById(memberId) }

    }

    @Test
    fun 페이징된_전체_모임_조회를_성공한다() {

        val pageQuery = mockk<MeetingQueryUseCase.PageQuery>(relaxed = true)

        every { meetingQueryAdapter.findMeetingByPaging(pageQuery.ofPageable()) } returns mockk(relaxed = true)

        this.sut.getMeetings(pageQuery)

        verify { meetingQueryAdapter.findMeetingByPaging(pageQuery.ofPageable()) }

    }

}