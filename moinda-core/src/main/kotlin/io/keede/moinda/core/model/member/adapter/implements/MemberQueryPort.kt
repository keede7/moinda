package io.keede.moinda.core.model.member.adapter.implements

import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.MemberJpaRepository
import org.springframework.stereotype.Service

@Service
internal class MemberQueryPort(
    private val memberJpaRepository: MemberJpaRepository
) : MemberQueryAdapter {

    override fun findById(memberId: Long): MemberJpaEntity {
        return memberJpaRepository.findById(memberId)
            .orElseThrow { RuntimeException() }
    }
}