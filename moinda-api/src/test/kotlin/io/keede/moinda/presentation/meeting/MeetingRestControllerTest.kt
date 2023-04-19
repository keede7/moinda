package io.keede.moinda.presentation.meeting

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.meeting.usecase.MeetingCommandUseCase
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriMaker
import io.keede.moinda.presentation.config.toMeetingApiUri
import io.keede.moinda.presentation.meeting.fixture.ofCreateMeetingDto
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import javax.servlet.http.Cookie

@WebMvcTest(MeetingRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MeetingRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var meetingCommandUseCase: MeetingCommandUseCase

    @MockkBean
    private lateinit var meetingQueryUseCase: MeetingQueryUseCase

    private lateinit var session: MockHttpSession

    private val cookie = Cookie(Constants.COOKIE_NAME, Constants.SESSION_KEY)

    @BeforeEach
    fun init() {
        this.session = MockHttpSession()
        this.session.setAttribute(Constants.SESSION_KEY, mockk<SessionResponse>())
    }

    @Test
    fun 모임_생성을_성공한다() {

        // Given
        val time = LocalDateTime.now().withNano(0)

        val sut = ofCreateMeetingDto(
            "모임1",
            "111",
            "주소1",
            "상세주소",
            "설명",
            30,
            time,
            time,
        )

        every { meetingCommandUseCase.create(any()) } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.MEETING_API)
                    .content(toJson(sut))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(this.session)
                    .cookie(this.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 특정_모임_조회를_성공한다() {

        // Given
        val meetingId = 1L

        every { meetingQueryUseCase.getById(any()) } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.toMeetingApiUri(meetingId))
                    .session(this.session)
                    .cookie(this.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 전체_모임_조회를_성공한다() {

        // Given
        every { meetingQueryUseCase.getMeetings() } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.MEETING_API)
                    .session(this.session)
                    .cookie(this.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }
}