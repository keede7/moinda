package io.keede.moinda.core.model.group.adapter.imlpements

import io.keede.moinda.common.group.CreateGroup
import io.keede.moinda.core.model.group.adapter.GroupCommandAdapter
import io.keede.moinda.core.model.group.entity.GroupJpaEntity
import io.keede.moinda.core.model.group.entity.GroupJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-03-18
 */
@Service
@Transactional
internal class GroupCommand(
    private val groupJpaRepository: GroupJpaRepository
) : GroupCommandAdapter {

    /**
     * override - 메서드를 구현할 떄 주로 사용한다.
     */
    override fun save(createGroup: CreateGroup): Long {
        val entity = GroupJpaEntity(
            createGroup.name,
            createGroup.description,
            createGroup.capacity
        )

        return groupJpaRepository.save(entity).id
    }
}