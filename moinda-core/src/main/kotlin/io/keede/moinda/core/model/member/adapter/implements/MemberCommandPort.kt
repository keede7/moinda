package io.keede.moinda.core.model.member.adapter.implements

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.MemberJpaRepository
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

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
            createMember.password,
            createMember.introduce
        )

        return memberJpaRepository.save(entity)
    }

    @PostConstruct
    fun initDummiesMember() {
        var entity = MemberJpaEntity(
            "테스트유저",
            "test1@naver.com",
            "1212",
            "안녕하세요?"
        )
        entity = memberJpaRepository.save(entity)
        println("더미 데이터 추가 : $entity")
    }
}