package io.keede.moinda.domains.meeting.usecase

import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-17
 */
interface MeetingBatchUseCase {

    fun cleanUpExpiredMeetings(today: LocalDateTime)

}