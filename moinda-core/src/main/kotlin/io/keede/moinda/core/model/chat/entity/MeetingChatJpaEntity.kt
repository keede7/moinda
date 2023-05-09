package io.keede.moinda.core.model.chat.entity

import io.keede.moinda.core.config.BaseEntity
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import java.time.LocalDateTime
import javax.persistence.*

/**
 * 모임에서만 사용되는 채팅
 * @author keede
 * Created on 2023-05-08
 */
@Entity
@Table(name = "meeting_chat_t")
class MeetingChatJpaEntity(

    @Lob
    @Column(name = "chat_context", nullable = false)
    val context : String,

    // nano 단위로 정렬의 기준이 될 수 있으므로,,
    @Column(name = "chat_write_at", nullable = false)
    val writeAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "meeting_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    val meetingJpaEntity: MeetingJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "member_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    val memberJpaEntity: MemberJpaEntity,

) : BaseEntity()