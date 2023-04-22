package io.keede.moinda.domains.meeting.domain

import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity

/**
 * @author keede
 * Created on 2023-04-18
 */
data class Meeting(
    private val meetingJpaEntity: MeetingJpaEntity
) {
    val meetingId = meetingJpaEntity.id
    val name = meetingJpaEntity.name
    val primaryAddress = meetingJpaEntity.location.primaryAddress
    val placeName = meetingJpaEntity.location.placeName
    val description = meetingJpaEntity.description
    val capacity = meetingJpaEntity.capacity
    val startAt = meetingJpaEntity.startAt
    val endAt = meetingJpaEntity.endAt
}