package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-04-06
 */
@UseCaseTest
internal class MemberCommandTest {

    @MockK
    private lateinit var memberCommandAdapter: MemberCommandAdapter

    @MockK
    private lateinit var memberQueryAdapter: MemberQueryAdapter

    @MockK
    private lateinit var groupQueryAdapter: GroupQueryAdapter

    @MockK
    private lateinit var meetingQueryAdapter: MeetingQueryAdapter

    private lateinit var sut: MemberCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MemberCommand(
            this.memberCommandAdapter,
            this.memberQueryAdapter,
            this.groupQueryAdapter,
            this.meetingQueryAdapter,
        )
    }

    @Test
    fun 사용자_회원가입_성공() {

        val command = mockk<MemberCommandUseCase.Command>()

        // Model(createMember) 를 할당해서 사용시에 실패한다. 이유는?
        every { memberCommandAdapter.save(command.createMember) } returns mockk(relaxed = true)

        sut.signup(command)

        verify { memberCommandAdapter.save(command.createMember) }

    }

    @Test
    fun 사용자_그룹참여_성공() {
        val participateToGroup = mockk<MemberCommandUseCase.ParticipateToGroup>(relaxed = true)

        every { groupQueryAdapter.findById(participateToGroup.groupId) } returns mockk(relaxed = true)
        every { memberQueryAdapter.findById(participateToGroup.memberId) } returns mockk(relaxed = true)

        sut.participate(participateToGroup)

        verify(exactly = 1) { groupQueryAdapter.findById(participateToGroup.groupId) }
        verify(exactly = 1) { memberQueryAdapter.findById(participateToGroup.memberId) }
    }

    @Test
    fun 사용자가_그룹_탈퇴_성공() {

        val leave = mockk<MemberCommandUseCase.LeaveGroup>(relaxed = true)

        every { memberQueryAdapter.findById(any()) } returns mockk(relaxed = true)

        sut.leave(leave)

        verify(exactly = 1) { memberQueryAdapter.findById(leave.memberId) }
    }

    @Test
    fun 사용자_모임참여_성공() {
        val participateToMeeting = mockk<MemberCommandUseCase.ParticipateToMeeting>(relaxed = true)

        every { meetingQueryAdapter.findById(participateToMeeting.meetingId) } returns mockk(relaxed = true)
        every { memberQueryAdapter.findById(participateToMeeting.memberId) } returns mockk(relaxed = true)

        sut.participate(participateToMeeting)

        verify(exactly = 1) { meetingQueryAdapter.findById(participateToMeeting.meetingId) }
        verify(exactly = 1) { memberQueryAdapter.findById(participateToMeeting.memberId) }
    }

}