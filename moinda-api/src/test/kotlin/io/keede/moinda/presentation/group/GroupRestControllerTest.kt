package io.keede.moinda.presentation.group

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import io.keede.moinda.domains.group.usecase.GroupQueryUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriMaker
import io.keede.moinda.presentation.config.toGroupApiUri
import io.keede.moinda.presentation.group.fixture.ofCreateGroupDto
import io.keede.moinda.presentation.group.fixture.ofLeaveGroupRequestDto
import io.keede.moinda.presentation.group.fixture.ofParticipateDto
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

/**
 * @author keede
 * Created on 2023-03-25
 */
@WebMvcTest(GroupRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class GroupRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var groupCommandUseCase: GroupCommandUseCase

    @MockkBean
    private lateinit var groupQueryUseCase: GroupQueryUseCase

    @MockkBean
    private lateinit var memberCommandUseCase: MemberCommandUseCase

    @BeforeEach
    fun init() {
        super.session = MockHttpSession()
        super.session.setAttribute(Constants.SESSION_KEY, mockk<SessionResponse>())
    }

    @Test
    fun 그룹_생성을_성공한다() {

        // Given
        val name: String = "그룹1"
        val description: String? = "설명111"
        val capacity: Int = 50

        val actual = ofCreateGroupDto(name, description, capacity)

        every { groupCommandUseCase.create(GroupCommandUseCase.Command(actual.toDomain())) } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.GROUP_API)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(toJson(actual))
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().isOk)
    }

    @Test
    fun 그룹_단일_조회를_성공한다() {
        //Given
        val groupId = 1L

        every { groupQueryUseCase.getGroupById(GroupQueryUseCase.Query(groupId)) } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.toGroupApiUri(groupId.toString()))
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform.andExpect(status().isOk)
    }

    @Test
    fun 그룹_전체_조회를_성공한다() {
        //Given
        every { groupQueryUseCase.getGroups() } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.GROUP_API)
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().isOk)
    }

    @Test
    fun 그룹_참여를_성공한다() {

        // Given
        val groupId = 1L
        val memberId = 1L

        val actual = ofParticipateDto(groupId, memberId)

        every {
            memberCommandUseCase.participate(
                MemberCommandUseCase.Participate(actual.groupId, actual.memberId)
            )
        } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.toGroupApiUri("participate"))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(toJson(actual))
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().isOk)
    }

    @Test
    fun 그룹_퇴장를_성공한다() {
        // Given
        val memberId = session.id.toLong()

        val actual = ofLeaveGroupRequestDto(memberId)

        every {
            memberCommandUseCase.leave(
                MemberCommandUseCase.Leave(actual.memberId)
            )
        } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.toGroupApiUri("leave"))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(toJson(actual))
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().isOk)
    }

}