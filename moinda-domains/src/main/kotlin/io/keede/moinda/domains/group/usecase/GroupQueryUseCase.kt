package io.keede.moinda.domains.group.usecase

import io.keede.moinda.domains.group.domain.Group

/**
 * @author keede
 * Created on 2023-03-25
 */
interface GroupQueryUseCase {

    // TODO : Naming
    fun findById(query: Query): Group

    fun getGroups(): List<Group>

    data class Query(
        val groupId: Long
    )
}