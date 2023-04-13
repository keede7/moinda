package io.keede.moinda.core.model.member.adapter.implements

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.QMemberJpaEntity
import io.keede.moinda.core.model.member.entity.QMemberJpaEntity.memberJpaEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

@Service
internal class MemberQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MemberQueryAdapter, QuerydslRepositorySupport(QMemberJpaEntity::class.java) {

    override fun findById(memberId: Long): MemberJpaEntity {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
            .where(memberJpaEntity.id.eq(memberId))
            .fetchOne() ?: throw RuntimeException()
    }

}