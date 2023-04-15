package io.keede.moinda.domains.member.usecase

import io.keede.moinda.domains.member.domain.Member

/**
 * @author keede
 * Created on 2023-04-15
 */
interface LoginUseCase {

    fun login(command: Command): Member

    data class Command(
        val email: String,
        val password: String
    )

}