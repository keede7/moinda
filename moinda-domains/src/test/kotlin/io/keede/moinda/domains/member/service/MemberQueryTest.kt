package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.port.MemberQueryPort
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-04-08
 */
@UseCaseTest
internal class MemberQueryTest {

    @MockK
    private lateinit var memberQueryPort: MemberQueryPort

    private lateinit var sut: MemberQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = MemberQuery(this.memberQueryPort)
    }

    @Test
    fun 사용자_단일_조회_성공한다() {

        val query = mockk<MemberQueryUseCase.Query>(relaxed = true)

        every { memberQueryPort.findById(query.memberId) } returns mockk(relaxed = true)

        this.sut.getById(query)

        verify(exactly = 1) { memberQueryPort.findById(query.memberId) }

    }

    @Test
    fun 특정_모임을_참가중인_사용자목록을_조회한다() {

        val query = mockk<MemberQueryUseCase.ParticipateMemberByMeetingId>(relaxed = true)

        every { memberQueryPort.findParticipateInMeetMembers(query.meetingId) } returns mockk(relaxed = true)

        this.sut.getParticipateInMeetMembers(query)

        verify(exactly = 1) { memberQueryPort.findParticipateInMeetMembers(query.meetingId) }

    }
}