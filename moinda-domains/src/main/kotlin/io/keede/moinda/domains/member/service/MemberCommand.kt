package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.group.port.GroupQueryPort
import io.keede.moinda.core.model.meeting.port.MeetingQueryPort
import io.keede.moinda.core.model.member.port.MemberCommandPort
import io.keede.moinda.core.model.member.port.MemberQueryPort
import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-04-06
 */
@Service
@Transactional
internal class MemberCommand(
    private val memberCommandPort: MemberCommandPort,
    private val memberQueryPort: MemberQueryPort,
    private val groupQueryPort: GroupQueryPort,
    private val meetingQueryPort: MeetingQueryPort,
) : MemberCommandUseCase {

    override fun signup(command: MemberCommandUseCase.Command): Member {

        memberQueryPort.existMemberByEmail(command.createMember.email)

        val entity = memberCommandPort.save(command.createMember)
        return Member(entity)
    }

    // TODO : 2차
    override fun participate(participate: MemberCommandUseCase.ParticipateToGroup) {
        val groupJpaEntity = groupQueryPort.findById(participate.groupId)
        // TODO : fetch 걸지 않는 조회성 메서드로 변경
        val memberJpaEntity = memberQueryPort.findById(participate.memberId)

//        memberJpaEntity.participate(groupJpaEntity)
    }

    // TODO : 2차
    override fun leave(leave: MemberCommandUseCase.LeaveGroup) {
        val memberJpaEntity = memberQueryPort.findById(leave.memberId)

//        memberJpaEntity.leaveGroup()
    }

    override fun participate(participate: MemberCommandUseCase.ParticipateToMeeting) {
        val memberJpaEntity = memberQueryPort.findById(participate.memberId)
        val meetingJpaEntity = meetingQueryPort.findById(participate.meetingId)

        memberJpaEntity.participate(meetingJpaEntity)
    }

    override fun leave(leave: MemberCommandUseCase.LeaveMeeting) {
        val memberJpaEntity = memberQueryPort.findById(leave.memberId)

        memberJpaEntity.leaveMeeting()
    }
}