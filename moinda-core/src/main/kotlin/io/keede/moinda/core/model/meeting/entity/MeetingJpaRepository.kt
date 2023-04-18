package io.keede.moinda.core.model.meeting.entity

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author keede
 * Created on 2023-04-18
 */
interface MeetingJpaRepository : JpaRepository<MeetingJpaEntity, Long> {
}