package io.keede.moinda.presentation.meeting

import com.ninjasquad.springmockk.MockkBean
import io.keede.moinda.common.member.session.Constants
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.meeting.usecase.MeetingCommandUseCase
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.keede.moinda.presentation.config.BaseApi
import io.keede.moinda.presentation.config.UriMaker
import io.keede.moinda.presentation.config.toMeetingApiUri
import io.keede.moinda.presentation.meeting.fixture.ofCreateMeetingDto
import io.keede.moinda.presentation.meeting.fixture.ofLeaveMeetingRequestDto
import io.keede.moinda.presentation.meeting.fixture.ofParticipateMeetingRequestDto
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(MeetingRestController::class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MeetingRestControllerTest : BaseApi() {

    @MockkBean
    private lateinit var meetingCommandUseCase: MeetingCommandUseCase

    @MockkBean
    private lateinit var meetingQueryUseCase: MeetingQueryUseCase

    @MockkBean
    private lateinit var memberCommandUseCase: MemberCommandUseCase

    @MockkBean
    private lateinit var memberQueryUseCase: MemberQueryUseCase

    @BeforeEach
    fun init() {
        super.session = MockHttpSession()
        super.session.setAttribute(Constants.SESSION_KEY, mockk<SessionResponse>())
    }

    @Test
    fun 모임_생성을_성공한다() {

        // Given
        val time = LocalDateTime.now().withNano(0)

        val sut = ofCreateMeetingDto(
            "모임1",
            "주소1",
            "스타벅스",
            "설명",
            30,
            time,
            time,
        )

        every { meetingCommandUseCase.create(any(), any()) } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.MEETING_API)
                    .content(toJson(sut))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(super.session)
                    .cookie(super.cookie)
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
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 페이징된_전체_모임_조회를_성공한다() {

        // Given
        val pagePaginatedMeetingRequestDto = mockk<PaginatedMeetingRequestDto>(relaxed = true)

        every {
            meetingQueryUseCase.getMeetings(
                MeetingQueryUseCase.PageQuery(
                    pagePaginatedMeetingRequestDto.page,
                    pagePaginatedMeetingRequestDto.size
                )
            )
        } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.toMeetingApiUri("paginated"))
                    .param("page", pagePaginatedMeetingRequestDto.page.toString())
                    .param("size", pagePaginatedMeetingRequestDto.size.toString())
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 모임에_참가한다() {

        // Given
        val meetingId = 1L
        val memberId = 1L

        val participateDto = ofParticipateMeetingRequestDto(
            meetingId,
            memberId,
        )

        every {
            memberCommandUseCase.participate(
                MemberCommandUseCase.ParticipateToMeeting(participateDto.meetingId, participateDto.memberId)
            )
        } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.toMeetingApiUri("participate"))
                    .content(toJson(participateDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 모임에_퇴장한다() {

        // Given
        val memberId = 1L

        val leaveMeetingRequestDto = ofLeaveMeetingRequestDto(
            memberId,
        )

        every {
            memberCommandUseCase.leave(
                MemberCommandUseCase.LeaveMeeting(leaveMeetingRequestDto.memberId)
            )
        } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                post(UriMaker.toMeetingApiUri("leave"))
                    .content(toJson(leaveMeetingRequestDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun 특정_모임의_참가자들을_조회한다() {

        // Given
        val meetingId = 1L;

        every {
            memberQueryUseCase.getParticipateInMeetMembers(
                MemberQueryUseCase.ParticipateMemberByMeetingId(meetingId)
            )
        } returns mockk(relaxed = true)

        // When
        val perform = super.mockMvc
            .perform(
                get(UriMaker.toMeetingApiUri(meetingId.toString(), "participant"))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .session(super.session)
                    .cookie(super.cookie)
            )

        // Then
        perform
            .andExpect(status().is2xxSuccessful)
    }
}