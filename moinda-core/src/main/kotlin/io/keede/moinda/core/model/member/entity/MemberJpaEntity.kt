package io.keede.moinda.core.model.member.entity

import io.keede.moinda.core.config.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

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
    var name: String,

    @Column(name = "email", length = 25, nullable = false)
    var email: String,

    @Column(name = "password", length = 300, nullable = false)
    var password: String,

    @Lob
    var introduce: String?,

    ) : BaseEntity() {

    // TODO : 2차
//    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
//    @JoinColumn(name = "group_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//    var groupEntity: GroupJpaEntity? = null
//
//    fun participate(groupEntity: GroupJpaEntity) {
//        this.groupEntity = groupEntity
//    }
//
//    fun leaveGroup() {
//        this.groupEntity = null
//    }

    fun isMatchPassword(password: String) {
        if (this.password != password) {
            throw RuntimeException("패스워드가 일치하지 않습니다.")
        }
    }

    override fun toString(): String {
        return "MemberJpaEntity: id: $id, name: $name"
    }
}