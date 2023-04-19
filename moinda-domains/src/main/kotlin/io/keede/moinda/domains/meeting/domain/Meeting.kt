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
    val postCode = meetingJpaEntity.location.postCode
    val primaryAddress = meetingJpaEntity.location.primaryAddress
    val detailAddress = meetingJpaEntity.location.detailAddress
    val description = meetingJpaEntity.description
    val capacity = meetingJpaEntity.capacity
    val startAt = meetingJpaEntity.startAt
    val endAt = meetingJpaEntity.endAt
}