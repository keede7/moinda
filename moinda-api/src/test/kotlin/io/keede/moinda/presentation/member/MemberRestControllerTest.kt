package io.keede.moinda.presentation.member

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.member.usecase.LoginUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriMaker
import io.keede.moinda.presentation.config.toMemberApiUri
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author keede
 * Created on 2023-04-08
 */
@WebMvcTest(MemberRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MemberRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var querySut: MemberQueryUseCase

    @MockkBean
    private lateinit var memberCommandUseCase: MemberCommandUseCase

    @MockkBean
    private lateinit var loginUseCase: LoginUseCase

    @BeforeEach
    fun init() {
        super.session = MockHttpSession()
        super.session.setAttribute(Constants.SESSION_KEY, mockk<SessionResponse>())
    }

    @Test
    fun 사용자_단일_조회_성공() {
        // Given
        val memberId = 1L

        every { querySut.getById(MemberQueryUseCase.Query(memberId)) } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.toMemberApiUri(memberId))
                    .session(super.session) // 없으면 만료관련 예외가 우선 발생한다, 다른 설정 및 코드를 먼저 바꿔야 가능.
                    .with(csrf())
                    .with(oauth2Login())
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

}