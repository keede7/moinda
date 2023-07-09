package io.keede.moinda.core.model.group.port

import io.keede.moinda.core.model.group.entity.GroupJpaEntity

/**
 * @author keede
 * Created on 2023-03-25
 */
interface GroupQueryPort {

    fun findById(groupId: Long): GroupJpaEntity

    fun findGroups(): List<GroupJpaEntity>

}