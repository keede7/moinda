package io.keede.moinda.core.model.chat.entity

import io.keede.moinda.core.config.BaseEntity
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

/**
 * 모임에서만 사용되는 채팅
 * @author keede
 * Created on 2023-05-08
 */
@Entity
@Table(name = "meeting_chat_t")
@DynamicUpdate
@DynamicInsert
class MeetingChatJpaEntity(

    @Lob
    @Column(name = "chat_context", nullable = false)
    val context : String,

    // nano 단위로 정렬의 기준이 될 수 있으므로,,
    @Column(name = "chat_write_at", nullable = false)
    val writeAt: LocalDateTime,


) : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "meeting_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var meetingJpaEntity: MeetingJpaEntity? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "member_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var memberJpaEntity: MemberJpaEntity? = null

    // NOTE : 최초 채팅시 해당 엔티티를 참조 시킬 때 사용
    fun initMeeting(meetingJpaEntity: MeetingJpaEntity) {
        this.meetingJpaEntity = meetingJpaEntity
    }
    // NOTE : 최초 채팅시 해당 엔티티를 참조 시킬 때 사용
    fun initMember(memberJpaEntity: MemberJpaEntity) {
        this.memberJpaEntity = memberJpaEntity
    }

}
