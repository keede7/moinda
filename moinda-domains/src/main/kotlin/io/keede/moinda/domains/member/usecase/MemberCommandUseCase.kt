package io.keede.moinda.domains.member.usecase

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.domains.member.domain.Member

/**
 * @author keede
 * Created on 2023-04-06
 */
interface MemberCommandUseCase {

    fun signup(command: Command): Member

    data class Command(
        val createMember: CreateMember
    )
}