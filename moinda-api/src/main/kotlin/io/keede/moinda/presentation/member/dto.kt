package io.keede.moinda.presentation.member

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.domains.member.domain.Member
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author keede
 * Created on 2023-04-06
 */

data class SignUpMemberDto(
    @field:NotNull(message = "이름을 입력하세요.")
    val name: String,
    @field:Email(message = "이메일 형식이 아닙니다.")
    @field:NotEmpty(message = "이메일을 입력하세요.")
    val email: String,
    val introduce: String?,
)

data class MemberResponseDto(
    val memberId: Long,
    val name: String,
    val email: String,
    val introduce: String?
)

internal fun SignUpMemberDto.toDomain() = CreateMember(
    name,
    email,
    introduce
)

internal fun Member.toMemberResponseDto() = MemberResponseDto(
    memberId,
    name,
    email,
    introduce
)