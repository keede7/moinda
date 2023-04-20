package io.keede.moinda.presentation.meeting

import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.domains.meeting.usecase.MeetingCommandUseCase
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.util.toResponseDtoList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author keede
 * Created on 2023-04-19
 */
@RestController
@RequestMapping("/api/meeting")
class MeetingRestController(
    private val meetingCommandUseCase: MeetingCommandUseCase,
    private val meetingQueryUseCase: MeetingQueryUseCase,
    private val memberCommandUseCase: MemberCommandUseCase,
) {

    @PostMapping
    fun create(
        @RequestBody @Valid createMeetingDto: CreateMeetingDto
    ): MeetingResponseDto =
        meetingCommandUseCase.create(
            MeetingCommandUseCase.Command(
                createMeetingDto.toDomain()
            )
        ).toMeetingResponseDto()

    @GetMapping("/{meetingId}")
    fun getOne(
        @PathVariable meetingId: Long
    ): MeetingResponseDto =
        meetingQueryUseCase.getById(
            MeetingQueryUseCase.Query(meetingId)
        ).toMeetingResponseDto()

    @GetMapping
    fun getList(): List<MeetingResponseDto> =
        meetingQueryUseCase.getMeetings()
            .toResponseDtoList(Meeting::toMeetingResponseDto)

    @PostMapping("/participate")
    fun participate(
        @RequestBody @Valid participateMeetingRequestDto: ParticipateMeetingRequestDto
    ) = memberCommandUseCase.participate(
        MemberCommandUseCase.ParticipateToMeeting(
            participateMeetingRequestDto.meetingId,
            participateMeetingRequestDto.memberId
        )
    )
}