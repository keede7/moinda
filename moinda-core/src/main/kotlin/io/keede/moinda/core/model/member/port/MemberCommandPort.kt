package io.keede.moinda.core.model.member.port

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.core.model.member.entity.MemberJpaEntity

/**
 * @author keede
 * Created on 2023-04-05
 */
interface MemberCommandPort {

    fun save(createMember: CreateMember): MemberJpaEntity

}