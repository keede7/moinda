package io.keede.moinda.domains.member.usecase

import io.keede.moinda.domains.member.domain.Member

interface MemberQueryUseCase {

    fun findById(query: Query) : Member

    data class Query(
        val memberId: Long
    )

}