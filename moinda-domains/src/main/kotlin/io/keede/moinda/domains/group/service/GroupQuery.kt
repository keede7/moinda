package io.keede.moinda.domains.group.service

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.domains.group.domain.Group
import io.keede.moinda.domains.group.usecase.GroupQueryUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-03-25
 */
@Service
@Transactional(readOnly = true)
class GroupQuery(
    private val groupQueryAdapter: GroupQueryAdapter
) : GroupQueryUseCase {

    override fun getGroupById(query: GroupQueryUseCase.Query): Group {
        val entity = groupQueryAdapter.findById(query.groupId)

        return Group(entity)
    }

    override fun getGroups(): List<Group> {
        val entities = groupQueryAdapter.findGroups()

        return entities.stream()
            .map { Group(entity = it) }
            .toList()
    }
}