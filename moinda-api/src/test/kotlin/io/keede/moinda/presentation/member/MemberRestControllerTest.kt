package io.keede.moinda.presentation.member

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.keede.moinda.presentation.config.*
import io.keede.moinda.presentation.member.fixture.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(MemberRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MemberRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var querySut: MemberQueryUseCase

    @MockkBean
    private lateinit var commandSut: MemberCommandUseCase

    @Test
    fun 회원가입_성공() {
        // Given
        val name = "테스트1"
        val email = "nana@naver.com"
        val introduce = "안녕하세요?"

        val sut = ofCreateMember(name, email, introduce)

        every { commandSut.signup(MemberCommandUseCase.Command(sut)) } returns mockk(relaxed = true)

        // When
        val perform = mockMvc
            .perform(
                post(UriMaker.MEMBER_API)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(toJson(sut))
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
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
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

}