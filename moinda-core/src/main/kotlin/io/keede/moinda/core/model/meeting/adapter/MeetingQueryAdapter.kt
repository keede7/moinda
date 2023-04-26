package io.keede.moinda.core.model.meeting.adapter

import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * @author keede
 * Created on 2023-04-18
 */
interface MeetingQueryAdapter {

    fun findById(meetingId: Long) : MeetingJpaEntity

    fun findMeetings() : List<MeetingJpaEntity>

    fun findMeetingByPaging(pageable: Pageable) : Page<MeetingJpaEntity>
}