package io.keede.moinda.presentation.group

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.domains.group.service.GroupCommand
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriProvider
import io.keede.moinda.presentation.group.fixture.ofCreateGroupDto
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
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
    private lateinit var groupCommand: GroupCommand

    private lateinit var groupCommandUseCase: GroupCommandUseCase

    @BeforeEach
    fun init() {
        this.groupCommandUseCase = this.groupCommand
    }

    @Test
    fun 그룹_생성을_성공한다() {

        val name: String = "그룹1"
        val description: String? = "설명111"
        val capacity: Int = 50

        // Given
        val sut = ofCreateGroupDto(name, description, capacity)

        every { groupCommandUseCase.create(GroupCommandUseCase.Command(sut.toDomain())) } returns mockk(relaxed = true)

        // When
        val perform = mockMvc.perform(
            post(UriProvider.GROUP_API)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(sut))
        )

        // Then
        perform.andExpect(status().isOk)
    }

}