package io.keede.moinda.presentation.meeting

import com.fasterxml.jackson.annotation.JsonFormat
import io.keede.moinda.common.PageResponse
import io.keede.moinda.common.meeting.CreateMeeting
import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.mapper.Paginator
import io.keede.moinda.util.toResponseDtoList
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author keede
 * Created on 2023-04-19
 */
data class CreateMeetingDto(
    @field:NotBlank(message = "모임 이름을 입력하세요.")
    val name: String,
    @field:NotBlank(message = "기본 주소가 필요합니다.")
    val primaryAddress: String,
    @field:NotBlank(message = "장소 명칭이 필요합니다.")
    val placeName: String,
    val description: String?,
    @field:NotNull(message = "최대 인원 수를 입력하세요.")
    val capacity: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", locale = "Asia/Seoul")
    val startAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", locale = "Asia/Seoul")
    val endAt: LocalDateTime,
)

data class ParticipateMeetingRequestDto(
    @field:NotNull(message = "모임 번호가 필요합니다.")
    val meetingId: Long,
    @field:NotNull(message = "사용자 번호가 필요합니다.")
    val memberId: Long,
)

data class LeaveMeetingRequestDto(
    @field:NotNull(message = "사용자 번호가 필요합니다.")
    val memberId: Long,
)

data class PaginatedMeetingRequestDto(
    @field:NotNull(message = "페이지 번호가 필요합니다.")
    val page: Int,
    val size: Int?,
)

data class MeetingResponseDto(
    val meetingId: Long,
    val name: String,
    val primaryAddress: String,
    val placeName: String,
    val description: String?,
    val capacity: Int,
    val startAt: String,
    val endAt: String,
    val numOfParticipants: Int,
)

internal fun CreateMeetingDto.toDomain() = CreateMeeting(
    name,
    primaryAddress,
    placeName,
    description,
    capacity,
    startAt,
    endAt,
)

internal fun Meeting.toMeetingResponseDto() = MeetingResponseDto(
    meetingId,
    name,
    primaryAddress,
    placeName,
    description,
    capacity,
    startAt,
    endAt,
    numOfParticipants,
)

internal fun Paginator<Meeting>.toPageResponse() = PageResponse<MeetingResponseDto>(
    page = this.page,
    size = this.size,
    totalPage = this.totalPage,
    totalCount = this.totalCount,
    keyword = this.keyword,
    start = this.start,
    end = this.end,
    prev = this.prev,
    next = this.next,
    domains = this.domains.toResponseDtoList(Meeting::toMeetingResponseDto),
    pagination = this.pagination,
)