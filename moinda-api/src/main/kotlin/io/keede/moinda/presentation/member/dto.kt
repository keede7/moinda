package io.keede.moinda.presentation.member

import io.keede.moinda.domains.member.domain.Member

/**
 * @author keede
 * Created on 2023-04-06
 */

data class MemberResponseDto(
    val memberId: Long?,
    val name: String,
    val email: String,
    val participatingMeetingId: Long?,
)

internal fun Member.toMemberResponseDto() = MemberResponseDto(
    memberId,
    name,
    email,
    participatingMeetingId,
)