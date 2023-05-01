package io.keede.moinda.core.model.meeting.entity

import com.querydsl.core.annotations.QueryProjection

/**
 * @author keede
 * Created on 2023-05-01
 */
class MeetingProjection @QueryProjection constructor(
    val meetingId: Long,
    val name: String,
    val primaryAddress: String,
    val placeName: String,
    val description: String?,
    val capacity: Int,
    val startAt: String,
    val endAt: String,
    val numOfParticipants: Int
)