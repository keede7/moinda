package io.keede.moinda.core.model.member.entity

import io.keede.moinda.core.config.BaseEntity
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

/**
 * 05-24 : OAuth2 기준으로 Entity 변경
 * @author keede
 * Created on 2023-04-05
 */
@Entity
@Table(name = "member_t")
@DynamicUpdate
@DynamicInsert
class MemberJpaEntity(

    @Column(name = "name", length = 10, nullable = false)
    @Comment("사용자 이름")
    var name: String,

    @Column(name = "email", length = 25, nullable = false)
    @Comment("사용자 이메일")
    var email: String,

    ) : BaseEntity() {

    // TODO : 2차
//    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
//    @JoinColumn(name = "group_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//    @Comment("그룹 번호")
//    var groupEntity: GroupJpaEntity? = null
//
//    fun participate(groupEntity: GroupJpaEntity) {
//        this.groupEntity = groupEntity
//    }
//
//    fun leaveGroup() {
//        this.groupEntity = null
//    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "meeting_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @Comment("모임 번호")
    var meetingJpaEntity: MeetingJpaEntity? = null

    fun participate(meetingJpaEntity: MeetingJpaEntity) {
        this.isParticipateInMeeting()
        this.meetingJpaEntity = meetingJpaEntity
    }

    fun leaveMeeting() {
        this.meetingJpaEntity = null
    }

    // TODO : 참여중인 아이디 하나를 제공한다. 2차 개발시 여러값으로 변환 될 수 있음,
    fun participatingMeetingId() : Long? {
        return this.meetingJpaEntity?.id
    }

    private fun isParticipateInMeeting() {
        if(this.meetingJpaEntity != null) {
            throw RuntimeException("1개의 모임만 참여할 수 있습니다.")
        }
    }

    override fun toString(): String {
        return "MemberJpaEntity: id: $id, name: $name"
    }
}