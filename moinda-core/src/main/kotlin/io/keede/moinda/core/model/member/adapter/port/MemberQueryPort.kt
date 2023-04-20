package io.keede.moinda.core.model.member.adapter.port

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.QMemberJpaEntity
import io.keede.moinda.core.model.member.entity.QMemberJpaEntity.memberJpaEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-04-05
 */
@Service
internal class MemberQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MemberQueryAdapter, QuerydslRepositorySupport(QMemberJpaEntity::class.java) {

    override fun findById(memberId: Long): MemberJpaEntity {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
//            .leftJoin(memberJpaEntity.groupEntity).fetchJoin()
            .where(
                memberJpaEntity.id.eq(memberId)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException()
    }

    // TODO : 용도에 따라서 비슷한 메서드를 만들 수 도 있다.
    override fun findByEmail(email: String): MemberJpaEntity {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
//            .leftJoin(memberJpaEntity.groupEntity).fetchJoin()
            .leftJoin(memberJpaEntity.meetingJpaEntity).fetchJoin()
            .where(
                memberJpaEntity.email.eq(email)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException("등록된 이메일이 아닙니다.")
    }
}