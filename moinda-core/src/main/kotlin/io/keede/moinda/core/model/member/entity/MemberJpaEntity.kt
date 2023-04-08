package io.keede.moinda.core.model.member.entity

import io.keede.moinda.core.config.BaseEntity
import io.keede.moinda.core.model.group.entity.GroupJpaEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

/**
 * @author keede
 * Created on 2023-04-05
 */
@Entity
@Table(name = "member_t")
@DynamicUpdate
class MemberJpaEntity(

    @Column(name = "name", length = 10, nullable = false)
    var name: String,

    @Column(name = "email", length = 25, nullable = false)
    var email: String,

    @Lob
    var introduce: String?,

) : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "group_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var groupEntity: GroupJpaEntity? = null

    fun participate(groupEntity: GroupJpaEntity) {
        this.groupEntity = groupEntity;
    }
}