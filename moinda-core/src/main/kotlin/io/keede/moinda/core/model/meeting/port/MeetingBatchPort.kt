package io.keede.moinda.core.model.meeting.port

import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-17
 */
interface MeetingBatchPort {

    fun findExpiredMeetings(today: LocalDateTime) : List<MeetingJpaEntity>

}