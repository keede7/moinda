package io.keede.moinda.core.model.group.adapter.implements

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.core.model.group.entity.GroupJpaEntity
import io.keede.moinda.core.model.group.entity.GroupJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-03-25
 */
@Service
@Transactional(readOnly = true)
internal class GroupQueryPort(
    private val groupJpaRepository: GroupJpaRepository
) : GroupQueryAdapter {

    override fun findById(groupId: Long): GroupJpaEntity {
        return groupJpaRepository.findById(groupId)
            .orElseThrow { RuntimeException() }
    }
}

