package io.keede.moinda.core.model.group.port

import io.keede.moinda.common.group.CreateGroup
import io.keede.moinda.core.model.group.entity.GroupJpaEntity

/**
 * 외부에 노출시켜서 다른 모듈이 사용할 수 있도록 한다.
 * @author keede
 * Created on 2023-03-18
 */
interface GroupCommandPort {

    fun save(createGroup: CreateGroup) : GroupJpaEntity

}