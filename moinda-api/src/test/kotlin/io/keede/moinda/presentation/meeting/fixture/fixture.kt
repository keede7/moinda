package io.keede.moinda.presentation.meeting.fixture

import io.keede.moinda.presentation.meeting.CreateMeetingDto
import io.keede.moinda.presentation.meeting.ParticipateMeetingRequestDto
import java.time.LocalDateTime

internal fun ofCreateMeetingDto(
    name: String,
    postCode: String,
    primaryAddress: String,
    detailAddress: String,
    description: String?,
    capacity: Int,
    startAt: LocalDateTime,
    endAt: LocalDateTime
) = CreateMeetingDto(
    name,
    postCode,
    primaryAddress,
    detailAddress,
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