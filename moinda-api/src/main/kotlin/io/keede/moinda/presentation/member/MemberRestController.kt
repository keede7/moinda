package io.keede.moinda.presentation.member

import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author keede
 * Created on 2023-04-06
 */
@RestController
@RequestMapping("/api/member")
class MemberRestController(
    private val memberCommandUseCase: MemberCommandUseCase
) {

    // TODO : Need To Write Test Code
    @PostMapping
    fun signup(
        @RequestBody @Valid signUpMemberDto: SignUpMemberDto
    ) : MemberResponseDto =
        memberCommandUseCase.signup(
            MemberCommandUseCase.Command(signUpMemberDto.toDomain())
        ).let(Member::toMemberResponseDto)

}