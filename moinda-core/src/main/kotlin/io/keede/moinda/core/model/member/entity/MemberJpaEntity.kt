package io.keede.moinda.core.model.member.entity

import io.keede.moinda.core.config.BaseEntity
import io.keede.moinda.core.model.meeting.entity.MeetingJpaEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

/**
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

    @Column(name = "password", length = 300, nullable = false)
    @Comment("사용자 패스워드")
    var password: String,

    @Lob
    @Comment("사용자 소개")
    var introduce: String?,

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
        this.meetingJpaEntity = meetingJpaEntity
    }

    fun leaveMeeting() {
        this.meetingJpaEntity = null
    }

    fun isMatchPassword(password: String) {
        if (this.password != password) {
            throw RuntimeException("패스워드가 일치하지 않습니다.")
        }
    }

    override fun toString(): String {
        return "MemberJpaEntity: id: $id, name: $name"
    }
}