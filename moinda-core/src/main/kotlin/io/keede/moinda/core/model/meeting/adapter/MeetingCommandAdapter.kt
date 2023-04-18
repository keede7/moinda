package io.keede.moinda.core.model.meeting.adapter

import io.keede.moinda.common.meeting.CreateMeeting
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity

/**
 * @author keede
 * Created on 2023-04-18
 */
interface MeetingCommandAdapter {

    fun save(createMeeting: CreateMeeting) : MeetingJpaEntity

}