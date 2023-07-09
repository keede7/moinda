package io.keede.moinda.core.model.member.port.adapter

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.core.model.member.port.MemberCommandPort
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.MemberJpaRepository
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * @author keede
 * Created on 2023-04-05
 */
@Service
internal class MemberCommandAdapter(
    private val memberJpaRepository: MemberJpaRepository
) : MemberCommandPort {

    override fun save(createMember: CreateMember): MemberJpaEntity {
        val entity = MemberJpaEntity(
            createMember.name,
            createMember.email,
        )

        return memberJpaRepository.save(entity)
    }

    // TODO : 추후삭제 더미추가
    @PostConstruct
    fun initDummiesMember() {
        var entity = MemberJpaEntity(
            "테스트유저1",
            "test1@naver.com",
        )
        entity = memberJpaRepository.save(entity)

        memberJpaRepository.save(
            MemberJpaEntity(
                "테스트유저2",
                "test2@naver.com",
            )
        )

        println("더미 데이터 추가 : $entity")
    }
}