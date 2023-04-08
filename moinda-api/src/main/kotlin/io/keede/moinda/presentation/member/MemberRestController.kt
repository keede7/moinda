package io.keede.moinda.presentation.member

import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author keede
 * Created on 2023-04-06
 */
@RestController
@RequestMapping("/api/member")
class MemberRestController(
    private val memberCommandUseCase: MemberCommandUseCase,
    private val memberQueryUseCase: MemberQueryUseCase
) {

    @PostMapping
    fun signup(
        @RequestBody @Valid signUpMemberDto: SignUpMemberDto
    ): MemberResponseDto =
        memberCommandUseCase.signup(
            MemberCommandUseCase.Command(signUpMemberDto.toDomain())
        ).let(Member::toMemberResponseDto)

    @GetMapping("/{memberId}")
    fun getOne(
        @PathVariable("memberId") memberId: Long
    ): MemberResponseDto =
        memberQueryUseCase.findById(
            MemberQueryUseCase.Query(memberId)
        ).let(Member::toMemberResponseDto)

}