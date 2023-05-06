package io.keede.moinda.domains.group.domain

import io.keede.moinda.core.model.group.entity.GroupJpaEntity

/**
 * @author keede
 * Created on 2023-03-18
 */
data class Group(
    val entity: GroupJpaEntity
) {
    val groupId: Long? = entity.id
    val name: String = entity.name
    val description: String? = entity.description
    val capacity: Int = entity.capacity
}