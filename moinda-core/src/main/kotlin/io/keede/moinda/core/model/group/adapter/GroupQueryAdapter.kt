package io.keede.moinda.core.model.group.adapter

import io.keede.moinda.core.model.group.entity.GroupJpaEntity

/**
 * @author keede
 * Created on 2023-03-25
 */
interface GroupQueryAdapter {

    fun findById(groupId: Long): GroupJpaEntity

}