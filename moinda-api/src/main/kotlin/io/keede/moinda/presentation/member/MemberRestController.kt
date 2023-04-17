package io.keede.moinda.presentation.member

import io.keede.moinda.common.member.session.Constants
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

    @PostMapping("/signup")
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

    @PostMapping("/logout")
    fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) = this.removeSession(request)

    @GetMapping("/{memberId}")
    fun getOne(
        @PathVariable("memberId") memberId: Long
    ): MemberResponseDto =
        memberQueryUseCase.findById(
            MemberQueryUseCase.Query(memberId)
        ).let(Member::toMemberResponseDto)

    private fun createSession(
        member: Member,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val session = request.getSession(true)

        val toSessionResponse = member.toSessionResponse()

        session.setAttribute(Constants.SESSION_KEY, toSessionResponse)
        session.maxInactiveInterval = 1800

        Cookie(Constants.COOKIE_NAME, Constants.SESSION_KEY)
            .also {
                it.maxAge = 1800
                it.path = "/"
                response.addCookie(it)
            }
    }

    private fun removeSession(
        request: HttpServletRequest
    ) {
        val session = request.getSession(false)

        session?.invalidate()
    }
}