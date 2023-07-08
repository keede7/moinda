package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.core.model.meeting.adapter.MeetingQueryAdapter
import io.keede.moinda.core.model.member.port.MemberCommandPort
import io.keede.moinda.core.model.member.port.MemberQueryPort
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
    private lateinit var memberCommandPort: MemberCommandPort

    @MockK
    private lateinit var memberQueryPort: MemberQueryPort

    @MockK
    private lateinit var groupQueryAdapter: GroupQueryAdapter

    @MockK
    private lateinit var meetingQueryAdapter: MeetingQueryAdapter

    private lateinit var sut: MemberCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MemberCommand(
            this.memberCommandPort,
            this.memberQueryPort,
            this.groupQueryAdapter,
            this.meetingQueryAdapter,
        )
    }

    @Test
    fun 사용자_회원가입_성공() {

        val command = mockk<MemberCommandUseCase.Command>(relaxed = true)

        // Model(createMember) 를 할당해서 사용시에 실패한다. 이유는?
        every { memberQueryPort.existMemberByEmail(command.createMember.email) } returns mockk(relaxed = true)
        every { memberCommandPort.save(command.createMember) } returns mockk(relaxed = true)

        sut.signup(command)

        verify { memberCommandPort.save(command.createMember) }
        verify { memberQueryPort.existMemberByEmail(command.createMember.email)  }

    }

    @Test
    fun 사용자_그룹참여_성공() {
        val participateToGroup = mockk<MemberCommandUseCase.ParticipateToGroup>(relaxed = true)

        every { groupQueryAdapter.findById(participateToGroup.groupId) } returns mockk(relaxed = true)
        every { memberQueryPort.findById(participateToGroup.memberId) } returns mockk(relaxed = true)

        sut.participate(participateToGroup)

        verify(exactly = 1) { groupQueryAdapter.findById(participateToGroup.groupId) }
        verify(exactly = 1) { memberQueryPort.findById(participateToGroup.memberId) }
    }

    @Test
    fun 사용자가_그룹_탈퇴_성공() {

        val leave = mockk<MemberCommandUseCase.LeaveGroup>(relaxed = true)

        every { memberQueryPort.findById(any()) } returns mockk(relaxed = true)

        sut.leave(leave)

        verify(exactly = 1) { memberQueryPort.findById(leave.memberId) }
    }

    @Test
    fun 사용자_모임참여_성공() {
        val participateToMeeting = mockk<MemberCommandUseCase.ParticipateToMeeting>(relaxed = true)

        every { meetingQueryAdapter.findById(participateToMeeting.meetingId) } returns mockk(relaxed = true)
        every { memberQueryPort.findById(participateToMeeting.memberId) } returns mockk(relaxed = true)

        sut.participate(participateToMeeting)

        verify(exactly = 1) { meetingQueryAdapter.findById(participateToMeeting.meetingId) }
        verify(exactly = 1) { memberQueryPort.findById(participateToMeeting.memberId) }
    }

    @Test
    fun 사용자_모임퇴장_성공() {
        val command = mockk<MemberCommandUseCase.LeaveMeeting>(relaxed = true)

        every { memberQueryPort.findById(command.memberId) } returns mockk(relaxed = true)

        sut.leave(command)

        verify(exactly = 1) { memberQueryPort.findById(command.memberId) }
    }

}