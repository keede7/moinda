package io.keede.moinda.presentation.group

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriProvider
import io.keede.moinda.presentation.group.fixture.ofCreateGroupDto
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(GroupRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class GroupRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var groupCommandUseCase: GroupCommandUseCase

    @MockkBean
    private lateinit var memberCommandUseCase: MemberCommandUseCase

    @Test
    fun 그룹_생성을_성공한다() {

        // Given
        val name: String = "그룹1"
        val description: String? = "설명111"
        val capacity: Int = 50

        val actual = ofCreateGroupDto(name, description, capacity)

        every { groupCommandUseCase.create(GroupCommandUseCase.Command(actual.toDomain())) } returns mockk(relaxed = true)

        // When
        val perform = mockMvc.perform(
            post(UriProvider.GROUP_API)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(actual))
        )

        // Then
        perform.andExpect(status().isOk)
    }

    @Test
    fun 그룹_참여를_성공한다() {

        // Given
        val groupId = 1L
        val memberId = 1L

        val actual = ParticipateDto(groupId, memberId)

        every { memberCommandUseCase.participate(
            MemberCommandUseCase.Participate(actual.groupId, actual.memberId)
        ) } returns mockk(relaxed = true)

        // When
        val perform = mockMvc.perform(
            post(String.format("%s%s%s", UriProvider.GROUP_API, UriProvider.PATH_SEPARATOR, "participate"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(actual))
        )

        // Then
        perform.andExpect(status().isOk)
    }

}