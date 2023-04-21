package io.keede.moinda.domains.member.usecase

import io.keede.moinda.domains.member.domain.Member

interface MemberQueryUseCase {

    fun getById(query: Query) : Member

    fun getParticipateInMeetMembers(participateMemberByMeetingId: ParticipateMemberByMeetingId) : List<Member>

    data class Query(
        val memberId: Long
    )

    data class ParticipateMemberByMeetingId(
        val meetingId: Long
    )

}