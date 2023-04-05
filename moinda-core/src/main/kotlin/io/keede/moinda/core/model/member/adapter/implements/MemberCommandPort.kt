package io.keede.moinda.core.model.member.adapter.implements

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.MemberJpaRepository
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-04-05
 */
@Service
internal class MemberCommandPort(
    private val memberJpaRepository: MemberJpaRepository
) : MemberCommandAdapter {

    override fun save(createMember: CreateMember): MemberJpaEntity {
        val entity = MemberJpaEntity(
            createMember.name,
            createMember.email,
            createMember.introduce
        )

        return memberJpaRepository.save(entity)
    }
}