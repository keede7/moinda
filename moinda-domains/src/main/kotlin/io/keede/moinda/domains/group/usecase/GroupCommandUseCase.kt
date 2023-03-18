package io.keede.moinda.domains.group.usecase

import io.keede.moinda.common.group.CreateGroup
import io.keede.moinda.core.model.group.entity.GroupJpaEntity
import io.keede.moinda.domains.group.domain.Group

/**
 * @author keede
 * Created on 2023-03-18
 */
interface GroupCommandUseCase {

    fun create(command: Command): Group

    data class Command(
        val createGroup: CreateGroup
    )

}