package io.keede.moinda.presentation.member

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.member.usecase.LoginUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.keede.moinda.presentation.config.*
import io.keede.moinda.presentation.member.fixture.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebMvcTest(MemberRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MemberRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var querySut: MemberQueryUseCase

    @MockkBean
    private lateinit var commandSut: MemberCommandUseCase

    @MockkBean
    private lateinit var loginSut: LoginUseCase

    private lateinit var session: MockHttpSession

    private val cookie = Cookie(Constants.COOKIE_NAME, Constants.SESSION_KEY)

    @BeforeEach
    fun init() {
        this.session = MockHttpSession()
        this.session.setAttribute(Constants.SESSION_KEY, mockk<SessionResponse>())
    }

    @Test
    fun 회원가입_성공() {
        // Given
        val name = "테스트1"
        val email = "nana@naver.com"
        val password = "1212"

        val sut = ofCreateMember(name, email, password)

        every { commandSut.signup(MemberCommandUseCase.Command(sut)) } returns mockk(relaxed = true)

        // When
        val perform = mockMvc
            .perform(
                post(UriMaker.toMemberApiUri("signup"))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(toJson(sut))
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 로그인_성공() {

        val email = "test@test.com"
        val password = "1212" // TODO : 추후 암호화

        val requestDto = ofLoginRequestDto(email, password)

        val request = mockk<HttpServletRequest>()
        val response = mockk<HttpServletResponse>()

        every { loginSut.login(any()) } returns mockk(relaxed = true)

        val perform = mockMvc
            .perform(
                post(
                    UriMaker.toMemberApiUri("login"),
                    request,
                    response
                ).content(toJson(requestDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )

        perform
            .andExpect(status().is2xxSuccessful)
            .andExpect(cookie().exists(Constants.COOKIE_NAME))
    }

    @Test
    fun 사용자_단일_조회_성공() {
        // Given
        val memberId = 1L

        every { querySut.findById(MemberQueryUseCase.Query(memberId)) } returns mockk(relaxed = true)

        // When
        val perform = mockMvc
            .perform(
                get(UriMaker.toMemberApiUri(memberId))
                    .session(session)
                    .cookie(cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

}