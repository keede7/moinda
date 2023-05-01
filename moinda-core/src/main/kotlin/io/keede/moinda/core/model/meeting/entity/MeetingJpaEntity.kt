package io.keede.moinda.core.model.meeting.entity

import io.keede.moinda.core.config.BaseEntity
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author keede
 * Created on 2023-04-18
 */
@Entity
@Table(name = "meeting_t")
@DynamicUpdate
class MeetingJpaEntity(

    @Column(name = "meeting_name", length = 50, nullable = false)
    @Comment(value = "모임 이름")
    var name: String,

    @Embedded
    var location: Location,

    @Column(name = "meeting_description", length = 300)
    @Comment(value = "모임 설명")
    var description: String?,

    @Column(name = "meeting_capacity", length = 10, nullable = false)
    @Comment(value = "모임최대정원")
    var capacity : Int = 4,

    @Column(name = "meeting_start_at")
    @Comment(value = "모임 시작 시간")
    var startAt: LocalDateTime,

    @Column(name = "meeting_end_at")
    @Comment(value = "모임 종료 시간")
    var endAt: LocalDateTime,

) : BaseEntity() {

    // TODO : 모임 참여자 수를 구하기 위해서 사용, 다른 방법이 있다면 다른 것을 사용.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meetingJpaEntity")
    val members : Set<MemberJpaEntity> = HashSet()

    override fun prePersist() {
        super.prePersist()
        bindMeetingTime()
    }

    override fun preUpdate() {
        super.preUpdate()
        bindMeetingTime()
    }

    private fun bindMeetingTime() {
        this.startAt = this.startAt.withNano(0)
        this.endAt = this.endAt.withNano(0)
    }
}