package io.keede.moinda.presentation.member

import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author keede
 * Created on 2023-04-06
 */
@RestController
@RequestMapping("/api/member")
class MemberRestController(
    private val memberQueryUseCase: MemberQueryUseCase,
) {

    @GetMapping("/{memberId}")
    fun getOne(
        @PathVariable("memberId") memberId: Long
    ): MemberResponseDto =
        memberQueryUseCase.getById(
            MemberQueryUseCase.Query(memberId)
        ).let(Member::toMemberResponseDto)

}