package io.keede.moinda.meeting

import io.keede.moinda.domains.meeting.usecase.MeetingBatchUseCase
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-17
 */
@Component
internal class MeetingBatchProcessor(
    private val meetingBatchUseCase: MeetingBatchUseCase
) {

    @Scheduled(cron = "0 0 0 * * ?")
    fun cleanUpExpiredMeetings() {
        println("Clean Up Expired Meeting..")
        val today = LocalDateTime.now().withSecond(0).withNano(0)
        meetingBatchUseCase.cleanUpExpiredMeetings(today)
    }

}