package io.keede.moinda.core.model.chat.entity

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author keede
 * Created on 2023-05-08
 */
interface MeetingChatJpaRepository : JpaRepository<MeetingChatJpaEntity, Long> {
}