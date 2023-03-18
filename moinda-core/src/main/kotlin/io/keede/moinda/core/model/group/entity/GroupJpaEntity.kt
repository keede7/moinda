package io.keede.moinda.core.model.group.entity

import io.keede.moinda.core.config.BaseEntity
import org.hibernate.annotations.Comment
import javax.persistence.*

/**
 * @author keede
 * Created on 2023-03-18
 */
@Entity
@Table(name = "group_t", indexes = [
    Index(name = "idx__group", columnList = "group_name")
])
class GroupJpaEntity(

    @Column(name = "group_name", length = 40, nullable = false)
    @Comment(value = "그룹명")
     val name : String,

    @Lob
    @Column(name = "group_description", length = 40)
    @Comment(value = "그룹소개")
     val description : String?,

    @Column(name = "group_capacity")
    @Comment(value = "그룹최대정원")
     val capacity : Int = 20

) : BaseEntity()