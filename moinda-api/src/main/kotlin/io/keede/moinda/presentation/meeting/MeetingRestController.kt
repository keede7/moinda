package io.keede.moinda.presentation.meeting

import io.keede.moinda.common.PageResponse
import io.keede.moinda.common.member.session.SessionResponse
import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.domains.meeting.usecase.MeetingCommandUseCase
import io.keede.moinda.domains.meeting.usecase.MeetingQueryUseCase
import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.keede.moinda.presentation.config.session.SessionUser
import io.keede.moinda.presentation.member.MemberResponseDto
import io.keede.moinda.presentation.member.toMemberResponseDto
import io.keede.moinda.util.toResponseDtoList
import org.springframework.web.bind.annotation.*
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
    private val memberQueryUseCase: MemberQueryUseCase
) {

    @PostMapping
    fun create(
        @SessionUser session: SessionResponse,
        @RequestBody @Valid createMeetingDto: CreateMeetingDto,
    ): MeetingResponseDto =
        meetingCommandUseCase.create(
            session,
            MeetingCommandUseCase.Command(
                createMeetingDto.toDomain()
            ),
        ).toMeetingResponseDto()

    @GetMapping("/{meetingId}")
    fun getOne(
        @PathVariable meetingId: Long
    ): MeetingResponseDto =
        meetingQueryUseCase.getById(
            MeetingQueryUseCase.Query(meetingId)
        ).toMeetingResponseDto()

    @GetMapping("/paginated")
    fun getPaginatedList(
       @ModelAttribute @Valid paginatedMeetingRequestDto: PaginatedMeetingRequestDto
    ): PageResponse<MeetingResponseDto> =
        meetingQueryUseCase.getMeetings(
            MeetingQueryUseCase.PageQuery(
                paginatedMeetingRequestDto.page,
                paginatedMeetingRequestDto.size,
            )
        ).toPageResponse()

    @GetMapping("/in-participating")
    fun getInParticipateMeeting(
        @SessionUser session: SessionResponse
    ): List<MeetingResponseDto> =
        meetingQueryUseCase.getInParticipatingMeetingsByMemberId(
            session.memberId
        ).toResponseDtoList(Meeting::toMeetingResponseDto)

    @GetMapping("/{meetingId}/participant")
    fun participate(
        @PathVariable meetingId: Long
    ): List<MemberResponseDto> =
        memberQueryUseCase.getParticipateInMeetMembers(
            MemberQueryUseCase.ParticipateMemberByMeetingId(
                meetingId
            )
        ).toResponseDtoList(Member::toMemberResponseDto)

    @PostMapping("/participate")
    fun participate(
        @RequestBody @Valid participateMeetingRequestDto: ParticipateMeetingRequestDto
    ) = memberCommandUseCase.participate(
        MemberCommandUseCase.ParticipateToMeeting(
            participateMeetingRequestDto.meetingId,
            participateMeetingRequestDto.memberId
        )
    )

    // TODO : 한명당 하나의 모임에만 들어가기 떄문에, 본인 아이디만으로 충분하게 할 수는 있다.
    // TODO : 하지만 이후에 meetingId 가 필요해 질 것.
    @PostMapping("/leave")
    fun leave(
        @RequestBody @Valid leaveMeetingRequestDto: LeaveMeetingRequestDto
    ) = memberCommandUseCase.leave(
        MemberCommandUseCase.LeaveMeeting(
            leaveMeetingRequestDto.memberId
        )
    )

}