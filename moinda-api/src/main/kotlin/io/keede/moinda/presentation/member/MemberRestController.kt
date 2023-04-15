package io.keede.moinda.presentation.member

import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.LoginUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

/**
 * @author keede
 * Created on 2023-04-06
 */
@RestController
@RequestMapping("/api/member")
class MemberRestController(
    private val memberCommandUseCase: MemberCommandUseCase,
    private val memberQueryUseCase: MemberQueryUseCase,
    private val loginUseCase: LoginUseCase
) {

    @PostMapping
    fun signup(
        @RequestBody @Valid signUpMemberDto: SignUpMemberDto
    ): MemberResponseDto =
        memberCommandUseCase.signup(
            MemberCommandUseCase.Command(signUpMemberDto.toDomain())
        ).let(Member::toMemberResponseDto)

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid loginRequestDto: LoginRequestDto,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val member = loginUseCase.login(
            LoginUseCase.Command(
                loginRequestDto.email,
                loginRequestDto.password
            )
        )

        this.createSession(member, request, response)
    }

    @GetMapping("/{memberId}")
    fun getOne(
        @PathVariable("memberId") memberId: Long
    ): MemberResponseDto =
        memberQueryUseCase.findById(
            MemberQueryUseCase.Query(memberId)
        ).let(Member::toMemberResponseDto)

    fun createSession(
        member: Member,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val session = request.getSession(true)

        val toSessionResponse = member.toSessionResponse()

        session.setAttribute("session", toSessionResponse)

        Cookie("login", "session")
            .also {
                it.maxAge = 1800
                it.path = "/"
                response.addCookie(it)
            }
    }
}