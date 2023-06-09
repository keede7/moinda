package io.keede.moinda.core.model.meeting.port.adapter

import io.keede.moinda.common.meeting.CreateMeeting
import io.keede.moinda.core.model.meeting.port.MeetingCommandPort
import io.keede.moinda.core.model.meeting.entity.Location
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.MeetingJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import javax.annotation.PostConstruct

/**
 * @author keede
 * Created on 2023-04-18
 */
@Service
@Transactional
internal class MeetingCommandAdapter(
    private val meetingJpaRepository: MeetingJpaRepository,
) : MeetingCommandPort {
    override fun save(createMeeting: CreateMeeting): MeetingJpaEntity {

        val meetingJpaEntity = MeetingJpaEntity(
            createMeeting.name,
            Location(
                createMeeting.primaryAddress,
                createMeeting.placeName
            ),
            createMeeting.description,
            createMeeting.capacity,
            // TODO: 화면에서 직접 DateTime 까지만 줄 수 도 있다.
            createMeeting.startAt.withNano(0),
            createMeeting.endAt.withNano(0)
        )

        return meetingJpaRepository.save(meetingJpaEntity)
    }

    // TODO : 추후 삭제
    @PostConstruct
    fun init() {
        for (index in 1..22) {
            val entity = MeetingJpaEntity(
                "오늘의 모임 $index",
                Location(
                    "우우우우우$index",
                    "여기입니다 $index",
                ),
                "설명$index",
                index,
                // TODO: 화면에서 직접 DateTime 까지만 줄 수 도 있다.
                LocalDateTime.now().withNano(0),
                LocalDateTime.now().withNano(0)
            )
            meetingJpaRepository.save(entity)
        }
    }
}