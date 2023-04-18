package io.keede.moinda.core.model.meeting.adapter

import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity

/**
 * @author keede
 * Created on 2023-04-18
 */
interface MeetingQueryAdapter {

    fun findById(meetingId: Long) : MeetingJpaEntity

    fun findMeetings() : List<MeetingJpaEntity>

}