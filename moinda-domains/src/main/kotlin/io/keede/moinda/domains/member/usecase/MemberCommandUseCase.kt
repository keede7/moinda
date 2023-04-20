package io.keede.moinda.domains.member.usecase

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.domains.member.domain.Member

/**
 * @author keede
 * Created on 2023-04-06
 */
interface MemberCommandUseCase {

    fun signup(command: Command): Member

    fun participate(participate: ParticipateToGroup)

    fun leave(leave: LeaveGroup)

    fun participate(participate: ParticipateToMeeting)

    data class Command(
        val createMember: CreateMember
    )

    data class ParticipateToGroup(
        val groupId: Long,
        val memberId: Long
    )

    data class LeaveGroup(
        val memberId: Long,
    )

    data class ParticipateToMeeting(
        val meetingId: Long,
        val memberId: Long,
    )

}