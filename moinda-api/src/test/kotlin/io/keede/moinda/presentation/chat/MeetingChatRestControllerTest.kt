package io.keede.moinda.presentation.chat

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.chat.usecase.MeetingChatCommandUseCase
import io.keede.moinda.presentation.chat.fixture.ofCreateMeetingChatDto
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriMaker
import io.keede.moinda.presentation.config.toMeetingChatApiUri
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime


/**
 * @author keede
 * Created on 2023-05-10
 */
@WebMvcTest(MeetingChatRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MeetingChatRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var meetingChatCommandUseCase: MeetingChatCommandUseCase

    @BeforeEach
    fun init() {
        super.session = MockHttpSession()
        super.session.setAttribute(Constants.SESSION_KEY, mockk<SessionResponse>())
    }

    @Test
    fun 채팅_전송을_성공한다() {

        // Given
        val createDto = ofCreateMeetingChatDto(
            "안녕하세요?",
            LocalDateTime.now().withNano(0),
            1,
            1,
        )

        every { meetingChatCommandUseCase.create(any()) } returns mockk(relaxed = true)
        // When

        val perform = super.mockMvc
            .perform(
                post(UriMaker.toMeetingChatApiUri("send"))
                    .content(toJson(createDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

}