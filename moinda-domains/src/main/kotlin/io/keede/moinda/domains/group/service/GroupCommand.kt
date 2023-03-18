package io.keede.moinda.domains.group.service

import io.keede.moinda.core.model.group.adapter.GroupCommandAdapter
import io.keede.moinda.core.model.group.entity.GroupJpaEntity
import io.keede.moinda.domains.group.domain.Group
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-03-18
 */
@Service
@Transactional
class GroupCommand(
    private val commandAdapter: GroupCommandAdapter
) : GroupCommandUseCase {

    override fun create(command: GroupCommandUseCase.Command): Group {
        val entity: GroupJpaEntity = commandAdapter.save(command.createGroup)

        // TODO : 매퍼를 사용하는 방향으로,
        return Group(entity)
    }
}
