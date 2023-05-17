package io.keede.moinda.core.model.meeting.adapter

import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-17
 */
interface MeetingBatchAdapter {

    fun findExpiredMeetings(today: LocalDateTime) : List<MeetingJpaEntity>

}