package io.keede.moinda.presentation.meeting.fixture

import io.keede.moinda.presentation.meeting.CreateMeetingDto
import io.keede.moinda.presentation.meeting.LeaveMeetingRequestDto
import io.keede.moinda.presentation.meeting.ParticipateMeetingRequestDto
import java.time.LocalDateTime

internal fun ofCreateMeetingDto(
    name: String,
    primaryAddress: String,
    placeName: String,
    description: String?,
    capacity: Int,
    startAt: LocalDateTime,
    endAt: LocalDateTime
) = CreateMeetingDto(
    name,
    primaryAddress,
    placeName,
    description,
    capacity,
    startAt,
    endAt,
)

internal fun ofParticipateMeetingRequestDto(
    meetingId: Long,
    memberId: Long,
) = ParticipateMeetingRequestDto(
    meetingId,
    memberId,
)

internal fun ofLeaveMeetingRequestDto(
    memberId: Long,
) = LeaveMeetingRequestDto(
    memberId,
)