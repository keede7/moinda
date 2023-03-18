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
/**
 * 외부의 모듈은 오로지 인터페이스만으로 소통하기 때문에
 * 외부에 노출시킬 필요가 없다.
 * 따라서 internal 로 더 안전하게 선언한다.
 */
internal class GroupJpaEntity(

    @Column(name = "group_name", length = 40, nullable = false)
    @Comment(value = "그룹명")
    private val name : String,

    @Lob
    @Column(name = "group_description", length = 40)
    @Comment(value = "그룹소개")
    private val description : String?,

    @Column(name = "group_capacity")
    @Comment(value = "그룹최대정원")
    private val capacity : Int = 20

) : BaseEntity()