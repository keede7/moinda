package io.keede.moinda.domains.meeting.domain

import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.MeetingProjection
import io.keede.moinda.util.toFullPattern

/**
 * @author keede
 * Created on 2023-04-18
 */
class Meeting(
    val meetingId: Long,
    val name: String,
    val primaryAddress: String,
    val placeName : String,
    val description: String?,
    val capacity: Int,
    val startAt: String,
    val endAt: String,
    val numOfParticipants: Int,
) {
    constructor(meetingJpaEntity: MeetingJpaEntity): this(
        meetingId = meetingJpaEntity.id,
        name = meetingJpaEntity.name,
        primaryAddress = meetingJpaEntity.location.primaryAddress,
        placeName = meetingJpaEntity.location.placeName,
        description = meetingJpaEntity.description,
        capacity = meetingJpaEntity.capacity,
        startAt = meetingJpaEntity.startAt.toFullPattern(),
        endAt = meetingJpaEntity.endAt.toFullPattern(),
        numOfParticipants = meetingJpaEntity.members.size,
    )

    constructor(meetingProjection: MeetingProjection) : this(
        meetingId = meetingProjection.meetingId,
        name = meetingProjection.name,
        primaryAddress = meetingProjection.primaryAddress,
        placeName = meetingProjection.placeName,
        description = meetingProjection.description,
        capacity = meetingProjection.capacity,
        startAt = meetingProjection.startAt,
        endAt = meetingProjection.endAt,
        numOfParticipants = meetingProjection.numOfParticipants,
    )

}