package io.keede.moinda.domains.meeting.service

import io.keede.moinda.core.model.meeting.adapter.MeetingBatchAdapter
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.domains.meeting.usecase.MeetingBatchUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-05-17
 */
@Service
@Transactional
internal class MeetingBatch(
    private val meetingBatchAdapter: MeetingBatchAdapter
) : MeetingBatchUseCase {

    // 당일 기준 종료날짜가 지났으면 삭제
    override fun cleanUpExpiredMeetings(today: LocalDateTime) {
        meetingBatchAdapter.findExpiredMeetings(today)
            .forEach(MeetingJpaEntity::remove)
    }
}