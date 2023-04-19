package io.keede.moinda.core.model.meeting.adapter.port

import io.keede.moinda.common.meeting.CreateMeeting
import io.keede.moinda.core.model.meeting.adapter.MeetingCommandAdapter
import io.keede.moinda.core.model.meeting.entity.Location
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.meeting.entity.MeetingJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-04-18
 */
@Service
@Transactional
internal class MeetingCommandPort(
    private val meetingJpaRepository: MeetingJpaRepository,
) : MeetingCommandAdapter {
    override fun save(createMeeting: CreateMeeting): MeetingJpaEntity {

        val meetingJpaEntity = MeetingJpaEntity(
            createMeeting.name,
            Location(
                createMeeting.postCode,
                createMeeting.primaryAddress,
                createMeeting.detailAddress
            ),
            createMeeting.description,
            createMeeting.capacity,
            // TODO: 화면에서 직접 DateTime 까지만 줄 수 도 있다.
            createMeeting.startAt.withNano(0),
            createMeeting.endAt.withNano(0)
        )

        return meetingJpaRepository.save(meetingJpaEntity)
    }
}